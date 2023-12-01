package com.cherrypicks.devops.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import springfox.documentation.annotations.ApiIgnore;


@ApiIgnore
public abstract class BaseController {

	protected static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    
}
