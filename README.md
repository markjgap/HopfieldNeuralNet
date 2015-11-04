# HopfieldNeuralNet

This is a Hopfield neural model that is used as an auto-associative memory to store and recall a set of bitmap images. 
Images are stored by computing a corresponding weight matrix. The system is equipped with two modes: training and testing.
In the training mode, the images to be stored are read from a file and generated weight matrix is saved to a file. 
The testing mode uses a data file with the same format as for training, where weights are read from a previously saved eight file.
The response of the net is generated for each testing input vector and the results are then written into another file.
The system has the ability to recall correctly and respond to incomplete or corrupted version of a store image under 30% of
its original image.

To begin the system, run MainHopfieldNetwork. For training demo use sampleTraining.txt and for testing 
the system after training use sampleTest.txt. A results file will be saved for review.
