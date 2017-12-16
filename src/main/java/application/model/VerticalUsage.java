package application.model;

import application.enums.Platform;

import javax.persistence.Entity;

//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//
//

@Entity
public class VerticalUsage {

    private int vertical;
    private long amount;

    public VerticalUsage(int vertical, long amount)
    {
        this.vertical=vertical;
        this.amount=amount;
    }

    public void setVertical (int vertical) {this.vertical=vertical;}
    public int getVertical () {return vertical;}
    public void setAmount (Long amount) {this.amount=amount;}
    public Long getAmount () {return  amount;}

}
