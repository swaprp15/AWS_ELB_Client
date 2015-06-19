package com.swap.aws.elb.client;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.swap.aws.elb.client.AWSHelper;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.elasticloadbalancing.model.ListenerDescription;
import com.amazonaws.services.elasticloadbalancing.model.LoadBalancerDescription;
import com.amazonaws.services.elasticloadbalancing.model.Instance;
import com.amazonaws.services.elasticloadbalancing.model.Listener;

public class Main {

	public static String configFile = "config.properties";

	public static String accessKeyConfigName = "AWSAccessKey";
	public static String secretKeyConfigName = "AWSSecretKey";
	public static String availabilityZoneConfigName = "availabilityZone";
	public static String httpProxyConfigName = "HttpProxy";

	public static String accessKey = null;
	public static String secretKey = null;
	public static String availabilityZone = null;
	public static String httpProxy = null;

	// Method to read configs file AWS access key, secret key, HTTP proxy etc.
	public static void readConfigurations() throws IOException {
		Properties properties = new Properties();

		InputStream input = null;

		try {
			input = new FileInputStream(configFile);

			properties.load(input);

			accessKey = properties.getProperty(accessKeyConfigName);
			secretKey = properties.getProperty(secretKeyConfigName);
			availabilityZone = properties
					.getProperty(availabilityZoneConfigName);
			httpProxy = properties.getProperty(httpProxyConfigName);
		} catch (IOException e) {
			throw e;
		} finally {
			input.close();
		}
	}

	public static void main(String[] args) {
		try {
			readConfigurations();

			BasicAWSCredentials awsCredentials = new BasicAWSCredentials(
					accessKey, secretKey);

			ClientConfiguration clientConfiguration = new ClientConfiguration();

			if (httpProxy != null && !httpProxy.equals(""))
				clientConfiguration.setProxyHost(httpProxy);

			clientConfiguration.setProtocol(Protocol.HTTP);
			clientConfiguration.setProxyPort(8080);

			AWSHelper awsHelper = new AWSHelper(accessKey, secretKey,
					availabilityZone);
			//
			// String loadBalancerName = "firstLB";
			// int instancePort = 80;
			// // String instanceProtocol = "HTTP";
			// int loadBalancerPort = 80;
			// String protocol = "HTTP";
			// // String availabilityZone = "ap-southeast-1b";
			//
			// Listener listener1 = new Listener(protocol, loadBalancerPort,
			// instancePort);
			// List<Listener> listeners = new ArrayList<Listener>();
			// listeners.add(listener1);
			//
			// instancePort = 9090;
			// // String instanceProtocol = "HTTP";
			// loadBalancerPort = 8090;
			// protocol = "HTTP";
			// // String availabilityZone = "ap-southeast-1b";
			//
			// Listener listener2 = new Listener(protocol, loadBalancerPort,
			// instancePort);
			// listeners.add(listener2);
			//
			//
			// String dnsNameOfLoadBalancer = awsHelper.createLoadBalancer(
			// loadBalancerName, listeners);
			//
			// if (dnsNameOfLoadBalancer != null)
			// System.out.println("Load balancer " + dnsNameOfLoadBalancer
			// + " created.");
			// else
			// System.out.println("Failed to create load balancer.");
			// // For now I have hard coded instance ID.
			// // We can use listRunningInstances() method of EC2 class to get
			// the
			// // list of all running instances and choose
			// Instance instance = new Instance();
			// instance.setInstanceId("i-d27b0022");
			//
			// List<Instance> instances = new ArrayList<Instance>();
			// instances.add(instance);
			//
			// awsHelper.registerInstancesToLoadBalancer(loadBalancerName,
			// instances);
			//
			// LoadBalancerDescription lbDescription = awsHelper
			// .getLoadBalancerDescription(loadBalancerName);
			//
			// instances = lbDescription.getInstances();
			//
			// System.out.println("Attached Instanes");
			//
			// for (Instance inst : instances) {
			// System.out.println(inst.getInstanceId());
			// }
			//
			//
			// List<ListenerDescription> listenerDescriptions =
			// lbDescription.getListenerDescriptions();
			//
			// System.out.println("Attached listeners.");
			//
			// for(ListenerDescription desc : listenerDescriptions)
			// {
			// System.out.println(desc.getListener().getInstanceProtocol());
			// }
			//
			// // Comment the following method for the first run to see
			// instances
			// // attached to load balancers
			// awsHelper.deregisterInstancesFromLoadBalancer(loadBalancerName,
			// instances);
			//
			// awsHelper.deleteLoadBalancer(loadBalancerName);

			String ip = "54.175.82.166";

			Instance instance = awsHelper.getInstanceByIP(ip);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}