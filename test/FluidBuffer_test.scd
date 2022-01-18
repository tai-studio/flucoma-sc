s.boot;

~buf = Buffer.read(s,FluidFilesPath("Nicol-LoopE-M.wav"));
~specbuf = FluidBuffer(s);
~statsbuf = FluidBuffer(s);

FluidBufSpectralShape.processBlocking(s,~buf,features:~specbuf,action:{"done".postln;});

~specbuf.frames; // posts: fftFrames (we can calc the # of them)
~specbuf.chans; // [ spectral centroid, spectral spread, normalized spectral skewness, normalized spectral kurtosis, spectral rolloff, spectral flatness, spectral crest ]

FluidBufStats.processBlocking(s,~specbuf,numChans:7,stats:~statsbuf,action:{"done".postln;});

~statsbuf.frames // [ mean, std dev, skewness, kurtosis, min, median, max ]
~statsbuf.chans // [ spectral centroid, spectral spread, normalized spectral skewness, normalized spectral kurtosis, spectral rolloff, spectral flatness, spectral crest ]

~statsbuf.getFrameChan(0,5); // "median spectral centroid"
~statsbuf.findLocation("median","spectral centroid"); // [0,5]

~statsbuf.postShape
/*
(but better:)
mean	std dev	skewness	kurtosis	min	median	max
-------------------------
spectral centroid	|	*	*	*	*	*	*	*
spectral spread	|	*	*	*	*	*	*	*
normalized spectral skewness	|	*	*	*	*	*	*	*
normalized spectral kurtosis	|	*	*	*	*	*	*	*
spectral rolloff	|	*	*	*	*	*	*	*
spectral flatness	|	*	*	*	*	*	*	*
spectral crest	|	*	*	*	*	*	*	*
*/