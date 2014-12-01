/**
 * 
 */
package de.uniluebeck.imi.mio.fhirProject.devices;

import java.util.List;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu.composite.ResourceReferenceDt;
import ca.uhn.fhir.model.dstu.resource.Device;
import ca.uhn.fhir.model.dstu.resource.Location;
import ca.uhn.fhir.model.dstu.resource.Observation;
import ca.uhn.fhir.model.dstu.resource.OperationOutcome;
import ca.uhn.fhir.model.primitive.IdDt;
import ca.uhn.fhir.rest.api.MethodOutcome;
import ca.uhn.fhir.rest.client.IGenericClient;
import ca.uhn.fhir.rest.gclient.IDelete;

/**
 * @author Daniel Rehmann
 *
 */
public class MIODeviceSystem implements IDevice {

    private String serverBase = "http://fhirtest.uhn.ca/base";
    FhirContext ctx = new FhirContext(); // TODO should be the same in every
					 // class
    IGenericClient client = ctx.newRestfulGenericClient(serverBase);

    /**
     * @param device the Device that will be created
     * @return true if created false if error or non create
     */
    public boolean createDeviceOnServer(Device device) {
	try {
	    MethodOutcome outcome = client.create().resource(device).prettyPrint()
		.encodedJson().execute();

	outcome.getId();
	return outcome.getCreated();
	}catch(Exception e){
	    System.err.println("create failed");
	    e.printStackTrace();
	    return false;
	}
    }

    /**
     * @param deviceID
     * @return true if deleted false if error
     */
    public boolean deleteDevice(IdDt deviceID) {
	try {

	    OperationOutcome outcome = client.delete().resourceById(deviceID)
		    .execute();
	    return true;
	} catch (Exception e) {
	    System.err.println("delete failed");
	    e.printStackTrace();
	    return false;
	}
    }

    public int createBasicInfrastructure() {
	// Location locToko = new Location().setName("Geburtsstation"); //TODO
	// location korrekt über Gruppe 2 abfragen
	Device tokometer = new Device();
	tokometer.setUdi("Tokometer1").setId(new IdDt("daniel"));

	createDeviceOnServer(tokometer);
	return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.uniluebeck.imi.mio.fhirProject.devices.IDevice#getDevice(java.lang
     * .String)
     */
    @Override
    public Device getDevice(String udi) {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.uniluebeck.imi.mio.fhirProject.devices.IDevice#getDevice(ca.uhn.fhir
     * .model.primitive.IdDt)
     */
    @Override
    public Device getDevice(IdDt deviceId) {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.uniluebeck.imi.mio.fhirProject.devices.IDevice#getDeviceForPatient
     * (ca.uhn.fhir.model.primitive.IdDt)
     */
    @Override
    public List<Device> getDeviceForPatient(IdDt patId) {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.uniluebeck.imi.mio.fhirProject.devices.IDevice#getObservationsForPatient
     * (ca.uhn.fhir.model.primitive.IdDt)
     */
    @Override
    public List<Observation> getObservationsForPatient(IdDt patId) {
	// TODO Auto-generated method stub
	return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.uniluebeck.imi.mio.fhirProject.devices.IDevice#PatientHasDevice(ca
     * .uhn.fhir.model.primitive.IdDt)
     */
    @Override
    public boolean PatientHasDevice(IdDt patId) {
	// TODO Auto-generated method stub
	return false;
    }

    public void setServerBase(String base) {
	this.serverBase = base;
    }

}
