package com.ysu.zyw.tc.components.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Mongo implements Serializable {

    private String id;

    private List<Orange> orangeList;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Orange implements Serializable {

        private String id;

    }

}
