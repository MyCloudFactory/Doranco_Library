package com.doranco.multitiers.mappers;

import com.doranco.multitiers.entity.Order;
import com.doranco.multitiers.entity.OrderLine;
import com.doranco.multitiers.entity.User;
import com.doranco.multitiers.vuesmodeles.OrderLineVM;
import com.doranco.multitiers.vuesmodeles.OrderVM;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.mapstruct.factory.Mappers;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-08-10T14:14:28+0200",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_181 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    private final OrderLineMapper orderLineMapper = Mappers.getMapper( OrderLineMapper.class );

    @Override
    public OrderVM entityToVm(Order entity) {
        if ( entity == null ) {
            return null;
        }

        OrderVM orderVM = new OrderVM();

        long id = entityUserId( entity );
        orderVM.setIdUser( id );
        orderVM.setId( entity.getId() );
        orderVM.setLines( orderLineMapper.entitiesToVMs( entity.getLines() ) );

        return orderVM;
    }

    @Override
    public Order vmToEntity(OrderVM vm) {
        if ( vm == null ) {
            return null;
        }

        Order order = new Order();

        order.setUser( orderVMToUser( vm ) );
        order.setLines( orderLineVMListToOrderLineList( vm.getLines() ) );

        return order;
    }

    @Override
    public List<OrderVM> entitiesToVMs(List<Order> entities) {
        if ( entities == null ) {
            return null;
        }

        List<OrderVM> list = new ArrayList<OrderVM>( entities.size() );
        for ( Order order : entities ) {
            list.add( entityToVm( order ) );
        }

        return list;
    }

    @Override
    public List<Order> vmsToEntities(List<OrderVM> vms) {
        if ( vms == null ) {
            return null;
        }

        List<Order> list = new ArrayList<Order>( vms.size() );
        for ( OrderVM orderVM : vms ) {
            list.add( vmToEntity( orderVM ) );
        }

        return list;
    }

    private long entityUserId(Order order) {
        if ( order == null ) {
            return 0L;
        }
        User user = order.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    protected User orderVMToUser(OrderVM orderVM) {
        if ( orderVM == null ) {
            return null;
        }

        User user = new User();

        user.setId( orderVM.getIdUser() );

        return user;
    }

    protected List<OrderLine> orderLineVMListToOrderLineList(List<OrderLineVM> list) {
        if ( list == null ) {
            return null;
        }

        List<OrderLine> list1 = new ArrayList<OrderLine>( list.size() );
        for ( OrderLineVM orderLineVM : list ) {
            list1.add( orderLineMapper.vmToEntity( orderLineVM ) );
        }

        return list1;
    }
}
