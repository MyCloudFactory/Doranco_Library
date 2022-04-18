package com.doranco.multitiers.mappers;

import com.doranco.multitiers.entity.User;
import com.doranco.multitiers.utils.Page;
import com.doranco.multitiers.vuesmodeles.UserVM;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-08-10T14:14:28+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserVM entityToVM(User user) {
        if ( user == null ) {
            return null;
        }

        UserVM userVM = new UserVM();

        userVM.setId( user.getId() );
        userVM.setFirstName( user.getFirstName() );
        userVM.setLastName( user.getLastName() );
        userVM.setUserName( user.getUserName() );
        userVM.setAdmin( user.isAdmin() );

        return userVM;
    }

    @Override
    public List<UserVM> entitiesToVMs(List<User> user) {
        if ( user == null ) {
            return null;
        }

        List<UserVM> list = new ArrayList<UserVM>( user.size() );
        for ( User user1 : user ) {
            list.add( entityToVM( user1 ) );
        }

        return list;
    }

    @Override
    public Page<UserVM> entitiesPageToVMsPage(Page<User> page) {
        if ( page == null ) {
            return null;
        }

        Page<UserVM> page1 = new Page<UserVM>();

        page1.setPAGESIZE( page.getPAGESIZE() );
        page1.setPageNumber( page.getPageNumber() );
        page1.setContent( entitiesToVMs( page.getContent() ) );
        page1.setTotalCount( page.getTotalCount() );

        return page1;
    }

    @Override
    public User VMToEntity(UserVM user) {
        if ( user == null ) {
            return null;
        }

        User user1 = new User();

        user1.setFirstName( user.getFirstName() );
        user1.setLastName( user.getLastName() );
        user1.setUserName( user.getUserName() );
        user1.setPassword( user.getPassword() );
        user1.setAdmin( user.isAdmin() );

        return user1;
    }
}
