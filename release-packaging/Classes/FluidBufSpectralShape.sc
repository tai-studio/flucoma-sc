FluidBufSpectralShape : FluidBufProcessor {

	*objectClassName{
		^\FluidBufSpecShp
	}

	*kr { |source, startFrame = 0, numFrames = -1, startChan = 0, numChans = -1, features, minFreq = 0, maxFreq = -1, rolloffPercent = 95, unit = 0, power = 0, windowSize = 1024, hopSize = -1, fftSize = -1, padding = 1, trig = 1, blocking = 0|

		var maxFFTSize = if (fftSize == -1) {windowSize.nextPowerOfTwo} {fftSize};

		source = source.asUGenInput;
		features = features.asUGenInput;

		source.isNil.if {"FluidBufSpectralShape:  Invalid source buffer".throw};
		features.isNil.if {"FluidBufSpectralShape:  Invalid features buffer".throw};

		^FluidProxyUgen.kr(this.objectClassName++\Trigger, -1, source, startFrame, numFrames, startChan, numChans, features, padding, minFreq, maxFreq, rolloffPercent, unit, power, windowSize, hopSize, fftSize, maxFFTSize, trig, blocking);

	}

	*process { |server, source, startFrame = 0, numFrames = -1, startChan = 0, numChans = -1, features, minFreq = 0, maxFreq = -1, rolloffPercent = 95, unit = 0, power = 0, windowSize = 1024, hopSize = -1, fftSize = -1, padding = 1, freeWhenDone = true, action|

		var maxFFTSize = if (fftSize == -1) {windowSize.nextPowerOfTwo} {fftSize};

		source = source.asUGenInput;
		features = features.asUGenInput;

		source.isNil.if {"FluidBufSpectralShape:  Invalid source buffer".throw};
		features.isNil.if {"FluidBufSpectralShape:  Invalid features buffer".throw};

		^this.new(
			server, nil, [features]
		).processList(
			[source, startFrame, numFrames, startChan, numChans, features, padding, minFreq, maxFreq, rolloffPercent, unit, power, windowSize, hopSize, fftSize, maxFFTSize, 0], freeWhenDone, action
		);
	}

	*processBlocking { |server, source, startFrame = 0, numFrames = -1, startChan = 0, numChans = -1, features, minFreq = 0, maxFreq = -1, rolloffPercent = 95, unit = 0, power = 0, windowSize = 1024, hopSize = -1, fftSize = -1, padding = 1, freeWhenDone = true, action|

		var maxFFTSize = if (fftSize == -1) {windowSize.nextPowerOfTwo} {fftSize};

		features.frames = "fftFrames (we can calc the # of them)";
		features.chans = ["spectral centroid","spectral spread","normalized spectral skewness","normalized spectral kurtosis","spectral rolloff","spectral flatness","spectral crest" ];

		source = source.asUGenInput;
		features = features.asUGenInput;

		source.isNil.if {"FluidBufSpectralShape:  Invalid source buffer".throw};
		features.isNil.if {"FluidBufSpectralShape:  Invalid features buffer".throw};

		^this.new(
			server, nil, [features]
		).processList(
			[source, startFrame, numFrames, startChan, numChans, features, padding, minFreq, maxFreq, rolloffPercent, unit, power, windowSize, hopSize, fftSize, maxFFTSize, 1], freeWhenDone, action
		);
	}
}
FluidBufSpectralShapeTrigger : FluidProxyUgen {}
