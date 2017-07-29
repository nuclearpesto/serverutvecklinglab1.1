package managedbeans;

import javax.faces.bean.ManagedBean;

/**
 * Created by archer on 2016-11-24.
 */
@ManagedBean(name = "navigationController")
public class NavigationBean {

    public String chats(){
        return "chats";
    }

    public String friends(){
        return "friends";
    }

    public String wall(){
        return "wall";
    }

}
