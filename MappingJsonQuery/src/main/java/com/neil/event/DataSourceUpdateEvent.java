package com.neil.event;

import com.neil.datasource.DataSourceProperties;
import org.springframework.context.ApplicationEvent;

public class DataSourceUpdateEvent extends ApplicationEvent {

    public DataSourceUpdateEvent(DataSourceProperties source) {
        super(source);
    }

}
