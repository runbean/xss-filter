package com.runbean.springboot01.service;

import org.springframework.stereotype.Service;

@Service
public class XssService {

    public String xss(){
        return "aaaaa<script>alert('haha - xss')</script>bbbb";
    }
}
