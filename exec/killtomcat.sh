#!/usr/bin/env bash
kill -9 `ps -ef |grep tomcat |awk {'print $2'}`
