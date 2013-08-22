package models;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.avaje.ebean.validation.Length;

import play.data.validation.Constraints;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

@Entity
public class User extends Model {

	@Id
	public Long id;
	
	

    
    @Column(length = 256, unique = true, nullable = false)
	@Required
	@MinLength(value=4)
	private String username;
	@Required
	public String name;
	
	
	@Transient
	@Required
	@MinLength(value=6)
	private String password;
	
	@Column(length = 256, unique = true, nullable = false)
    @Constraints.MaxLength(256)
    @Constraints.Required
    @Constraints.Email
	private String email;
	
	@Column(length = 64, nullable = false)
    private byte[] shaPassword;
	
	
	
	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
		shaPassword=getSha512(password);
	}
	


	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public User(String name,String username,String password,String email)
	{
		setEmail(email);
		setPassword(password);
		setUsername(username);
		this.name=name;
		
	}
	
	public static byte[] getSha512(String value) {
        try {
            return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
        	//return MessageDigest.getInstance(algorithm)
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
	
	
	
	public static Finder<Long, User> find = new Finder<Long, User>(Long.class, User.class);
	
	
	
	
	public static User authenticate(String username, String password) {

        
        User user = find.where().eq("username", username).eq("shaPassword", getSha512(password)).findUnique();
        if (user != null) {
            // get the hash password from the salt + clear password
            
              return user;
            
        }
        return null;
    }
	public static User findByusernameAndPassword(String username, String password) {
        // todo: verify this query is correct.  Does it need an "and" statement?
        return find.where().eq("username", username).eq("shaPassword", getSha512(password)).findUnique();
    }
	
	
	
	
}
