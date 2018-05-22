package com.example.GBanque.dao;


import com.example.GBanque.entity.BanqueAccount;
import com.example.GBanque.model.BanqueAccountInfo;
import com.example.GBanque.exception.BanqueTransactionException;
import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;




import java.util.List;

@Data
@Repository
@Transactional
public class BanqueAccountDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public BanqueAccount findById(long id){
        Session session = this.sessionFactory.getCurrentSession();
        return session.get(BanqueAccount.class,id);
    }

    public List<BanqueAccountInfo> listBanqueAccountInfo(){

        String sql ="Select new"+ BanqueAccountInfo.class.getName()//
                +"(e.id,e.fullName,e.balance)" //
                +"from"+ BanqueAccount.class.getName()+"e";
        Session session=this.sessionFactory.getCurrentSession();
        Query<BanqueAccountInfo> query= session.createQuery(sql,BanqueAccountInfo.class);
        return query.getResultList();
    }

    @Transactional(propagation =Propagation.MANDATORY)
    public void addAmount(Long id,double amount) throws BanqueTransactionException{

        BanqueAccount account= this.findById(id);
        if(account == null)
            throw new BanqueTransactionException("Account not found"+id);

        double newBalance = account.getBalance()+amount;
        if (newBalance <0)
            throw  new BanqueTransactionException("The money in the account"+id+"is not enough ("+account.getBalance()+")");
        account.setBalance(newBalance);

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = BanqueTransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount) throws BanqueTransactionException {

        addAmount(toAccountId, amount);
        addAmount(fromAccountId, -amount);
    }
}
