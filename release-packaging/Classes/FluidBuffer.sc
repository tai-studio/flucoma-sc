FluidBuffer : Buffer {
	var <>chans, <>frames;

	getFrameChan {
		arg c, f;
		^"% %".format(frames[f],chans[c]);
	}

	postShape {
		frames.do{arg f; "%\t".format(f).post};
		"".postln;
		{"-".post} ! 25;
		"".postln;
		chans.do{
			arg c;
			"%\t|".format(c).post;
			{"\t*".post} ! frames.size;
			"".postln;
		};
	}
}