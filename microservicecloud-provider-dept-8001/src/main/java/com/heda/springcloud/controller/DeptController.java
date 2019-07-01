package com.heda.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.heda.springcloud.entities.Dept;
import com.heda.springcloud.service.DeptService;

@RestController
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	@Autowired
	private DiscoveryClient discoveryClient;

	@RequestMapping(value="/dept/add",method=RequestMethod.POST)
	public boolean add(@RequestBody Dept dept) {
		return deptService.add(dept);
	}
	
	//http://localhost:8001/dept/1
	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id) {
		return deptService.get(id);
	}

//	http://localhost:8001/dept/get?id=1
//	@RequestMapping(value="/dept/get",method=RequestMethod.GET)
//	public Dept get(@RequestParam("id") Long id) {
//		System.out.println(id);
//		return deptService.get(id);
//	}
	
	@RequestMapping(value="/dept/list",method=RequestMethod.GET)
	public List<Dept> list(){
		return deptService.list();
	}
	
	@RequestMapping(value="/dept/discovery",method=RequestMethod.GET)
	public Object discovery() {
		List<String> services = discoveryClient.getServices();
		services.forEach(x->{
			System.out.println("-----"+x);
		});
		
		List<ServiceInstance> instances = discoveryClient.getInstances("microservicecloud-dept");
		instances.forEach(i->{
			System.out.println("serviceId:"+i.getServiceId()+",host:"+i.getHost()+",port:"+i.getPort()+",uri:"+i.getUri());
		});
		return this.discoveryClient;
	}
	
}
