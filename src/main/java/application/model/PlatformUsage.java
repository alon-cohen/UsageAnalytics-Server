package application.model;

//
///**
// * Created by matan on 09/05/2016.
// */
//
import application.enums.Platform;
import com.fasterxml.jackson.annotation.JsonIgnore;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.AuthorityUtils;
//
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.List;
//

@Entity
public class PlatformUsage {

    private Platform platform;
    private long usageAmount;

    public PlatformUsage(Platform platform, long usageAmount)
    {
        this.platform=platform;
        this.usageAmount=usageAmount;
    }

    public void setPlatform (Platform platform) {this.platform=platform;}
    public Platform getPlatform () {return platform;}
    public void setUsageAmount (Long usageAmount) {this.usageAmount=usageAmount;}
    public Long getUsageAmount () {return  usageAmount;}

}
