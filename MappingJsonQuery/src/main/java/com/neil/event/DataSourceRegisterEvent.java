package com.neil.event;

import com.neil.datasource.DataSourceProperties;
import org.springframework.context.ApplicationEvent;

public class DataSourceRegisterEvent extends ApplicationEvent {

    public DataSourceRegisterEvent(DataSourceProperties source) {
        super(source);
    }

}
