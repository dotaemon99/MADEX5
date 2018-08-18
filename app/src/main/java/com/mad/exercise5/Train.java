package com.mad.exercise5;

/**
 * Train class for the model of train
 */

public class Train {
    private String mPlatform;
    private int mArrivalTime;
    private String mStatus;
    private String mDestination;
    private String mDestinationTime;

    /**
     * constructor for train
     * @param platform for the platform string
     * @param arrivalTime for arrival time
     * @param status for mStatus
     * @param destination for mDestination
     * @param destinationTime for time mDestination
     */
    public Train(String platform, int arrivalTime, String status, String destination, String destinationTime){
        mPlatform = platform;
        mArrivalTime = arrivalTime;
        mStatus = status;
        mDestination = destination;
        mDestinationTime = destinationTime;
    }

    /**
     * get the platform
     * @return platform
     */
    public String getPlatform() {
        return mPlatform;
    }

    /**
     * set the platform
     * @param platform set the platform
     */
    public void setPlatform(String platform) {
        mPlatform = platform;
    }

    /**
     * get the arrival time
     * @return arrivaltime
     */
    public int getArrivalTime() {
        return mArrivalTime;
    }

    /**
     * set the arrival time
     * @param arrivalTime for arrivaltime
     */
    public void setArrivalTime(int arrivalTime) {
        mArrivalTime = arrivalTime;
    }

    /**
     * get the mStatus
     * @return mStatus
     */
    public String getStatus() {
        return mStatus;
    }

    /**
     * set the mStatus
     * @param status for the mStatus change
     */
    public void setStatus(String status) {
        mStatus = status;
    }

    /**
     * get the mDestination place
     * @return mDestination
     */
    public String getDestination() {
        return mDestination;
    }

    /**
     * set the mDestination place
     * @param destination for mDestination
     */
    public void setDestination(String destination) {
        mDestination = destination;
    }

    /**
     * get the mDestination time
     * @return mDestination time
     */
    public String getDestinationTime() {
        return mDestinationTime;
    }

    /**
     * set mDestination time
     * @param destinationTime for the mDestination time
     */
    public void setDestinationTime(String destinationTime) {
        mDestinationTime = destinationTime;
    }
}
