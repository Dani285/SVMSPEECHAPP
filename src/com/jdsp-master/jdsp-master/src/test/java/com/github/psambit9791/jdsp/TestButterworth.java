/*
 * Copyright (c) 2020 Sambit Paul
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package com.github.psambit9791.jdsp;

import com.github.psambit9791.jdsp.filter.Butterworth;
import com.github.psambit9791.jdsp.filter._IIRFilter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestButterworth {

    // 5Hz Sine + 15Hz Sine + 30Hz Sine sampled @ 100Hz with Nyquist of 50Hz
    private double[] signal1 = {0.0, 2.069, 0.951, 0.53, 1.314, 0.0, -0.588, 1.706, 2.127, 0.167, -0.0, -0.167, -2.127,
            -1.706, 0.588, 0.0, -1.314, -0.53, -0.951, -2.069, -0.0, 2.069, 0.951, 0.53, 1.314, 0.0, -0.588, 1.706,
            2.127, 0.167, -0.0,-0.167, -2.127, -1.706, 0.588, -0.0, -1.314, -0.53, -0.951, -2.069, -0.0, 2.069, 0.951,
            0.53, 1.314, 0.0,-0.588, 1.706, 2.127, 0.167, -0.0, -0.167, -2.127, -1.706, 0.588, 0.0, -1.314, -0.53,
            -0.951, -2.069, -0.0, 2.069, 0.951, 0.53, 1.314, 0.0, -0.588, 1.706, 2.127, 0.167, -0.0, -0.167, -2.127,
            -1.706, 0.588, 0.0, -1.314, -0.53, -0.951, -2.069, -0.0, 2.069, 0.951, 0.53, 1.314, 0.0, -0.588, 1.706,
            2.127, 0.167, -0.0, -0.167, -2.127, -1.706, 0.588, 0.0, -1.314, -0.53, -0.951, -2.069};

    // 2Hz Sine + 20Hz Sine + 45Hz Sine sampled @ 100Hz with Nyquist of 50Hz
    private double[] signal2 = {0.0, 1.385, 0.249, 0.589, -1.42, 1.588, 0.685, 2.167, -0.331, 0.263, 0.951, 1.624, 2.174,
            -0.399, 0.982, -0.049, 2.807, 0.623, 0.771, -0.576, 0.588, 1.742, 0.368, 0.47, -1.777, 1.0, -0.125, 1.148,
            -1.544, -1.124, -0.588, -0.043, 0.405, -2.241, -0.905, -1.951, 0.92, -1.219, -0.998, -2.242, -0.951, 0.355,
            -0.844, -0.549, -2.587, 0.412, -0.482, 1.029, -1.424, -0.767, -0.0, 0.767, 1.424, -1.029, 0.482, -0.412,
            2.587, 0.549, 0.844, -0.355, 0.951, 2.242, 0.998, 1.219, -0.92, 1.951, 0.905, 2.241, -0.405, 0.043, 0.588,
            1.124, 1.544, -1.148, 0.125, -1.0, 1.777, -0.47, -0.368, -1.742, -0.588, 0.576, -0.771, -0.623, -2.807,
            0.049, -0.982, 0.399, -2.174, -1.624, -0.951, -0.263, 0.331, -2.167, -0.685, -1.588, 1.42, -0.589, -0.249,
            -1.385};

    private Butterworth flt = new Butterworth(100);

    @Test
    public void LowPassTest1() {
        final double[] out = {0.0, 0.007, 0.049, 0.161, 0.35, 0.575, 0.774, 0.881, 0.879, 0.822, 0.779, 0.763, 0.726, 0.598,
                0.33, -0.049, -0.431, -0.713, -0.861, -0.901, -0.89, -0.867, -0.805, -0.633, -0.325, 0.071, 0.461,
                0.747, 0.881, 0.905, 0.891, 0.862, 0.792, 0.624, 0.324, -0.075, -0.463, -0.741, -0.878, -0.907, -0.887,
                -0.859, -0.795, -0.625, -0.32, 0.072, 0.46, 0.745, 0.879, 0.903, 0.889, 0.862, 0.792, 0.625, 0.324,
                -0.074, -0.463, -0.741, -0.879, -0.907, -0.887, -0.859, -0.795, -0.625, -0.32, 0.072, 0.46, 0.745,
                0.879, 0.903, 0.889, 0.862, 0.792, 0.625, 0.324, -0.074, -0.463, -0.741, -0.879, -0.907, -0.887, -0.859,
                -0.795, -0.625, -0.32, 0.072, 0.46, 0.745, 0.879, 0.903, 0.889, 0.862, 0.792, 0.625, 0.324, -0.074,
                -0.463, -0.741, -0.879, -0.907};

        double[] result = flt.lowPassFilter(this.signal1, 4, 9);
        Assertions.assertArrayEquals(result, out, 0.001);
    }

    @Test
    public void LowPassTest2() {
        final double[] out = {0.0, 0.001, 0.004, 0.015, 0.037, 0.068, 0.104, 0.145, 0.192, 0.249, 0.315, 0.385, 0.458,
                0.533, 0.613, 0.694, 0.768, 0.833, 0.888, 0.935, 0.97, 0.987, 0.985, 0.966, 0.933, 0.886, 0.821, 0.739,
                0.644, 0.541, 0.431, 0.312, 0.186, 0.057, -0.07, -0.193, -0.315, -0.434, -0.546, -0.646, -0.734, -0.812,
                -0.88, -0.934, -0.971, -0.991, -0.997, -0.99, -0.968, -0.928, -0.871, -0.803, -0.725, -0.635, -0.533,
                -0.42, -0.302, -0.183, -0.06, 0.066, 0.193, 0.315, 0.43, 0.537, 0.639, 0.733, 0.814, 0.878, 0.929,
                0.968, 0.994, 1.002, 0.991, 0.965, 0.926, 0.875, 0.808, 0.726, 0.631, 0.53, 0.423, 0.307, 0.183, 0.056,
                -0.069, -0.191, -0.311, -0.43, -0.542, -0.642, -0.731, -0.809, -0.878, -0.933, -0.971, -0.991, -0.997,
                -0.991, -0.969, -0.929};

        double[] result = flt.lowPassFilter(this.signal2,4, 5);
        Assertions.assertArrayEquals(result, out, 0.001);
    }

    @Test
    public void HighPassTest1() {
        final double[] out = {0.0, 0.112, -0.468, 0.721, -0.364, -0.356, 0.688, -0.217, -0.6, 0.651, 0.227, -0.773,
                0.269, 0.579, -0.677, -0.178, 0.812, -0.282, -0.609, 0.651, 0.167, -0.793, 0.318, 0.629, -0.663, -0.2,
                0.766, -0.316, -0.6, 0.696, 0.21, -0.788, 0.282, 0.581, -0.684, -0.177, 0.814, -0.284, -0.61, 0.651,
                0.167, -0.793, 0.318, 0.629, -0.663, -0.2, 0.766, -0.316, -0.6, 0.696, 0.21, -0.788, 0.282, 0.581,
                -0.684, -0.177, 0.814, -0.284, -0.61, 0.651, 0.167, -0.793, 0.318, 0.629, -0.663, -0.2, 0.766, -0.316,
                -0.6, 0.696, 0.21, -0.788, 0.282, 0.581, -0.684, -0.177, 0.814, -0.284, -0.61, 0.651, 0.167, -0.793,
                0.318, 0.629, -0.663, -0.2, 0.766, -0.316,-0.6, 0.696, 0.21, -0.788, 0.282, 0.581, -0.684, -0.177,
                0.814, -0.284, -0.61, 0.651};

        double[] result = flt.highPassFilter(this.signal1, 4, 29);
        Assertions.assertArrayEquals(result, out, 0.001);
    }

    @Test
    public void HighPassTest2() {
        final double[] out = {0.0, 0.007, -0.041, 0.121, -0.235, 0.374, -0.54, 0.714, -0.853, 0.933, -0.938, 0.843,
                -0.651, 0.393, -0.089, -0.235, 0.53, -0.767, 0.933, -1.006, 0.971, -0.845, 0.642, -0.37, 0.062, 0.244,
                -0.53, 0.77, -0.927, 0.993, -0.969, 0.847, -0.636, 0.371, -0.07, -0.246, 0.533, -0.764, 0.927, -0.999,
                0.966, -0.843, 0.642, -0.371, 0.064, 0.243, -0.53, 0.77, -0.927, 0.993, -0.97, 0.847, -0.637, 0.371,
                -0.07, -0.246, 0.534, -0.764, 0.927, -0.999, 0.966, -0.843, 0.642, -0.371, 0.064, 0.243, -0.53, 0.77,
                -0.927, 0.993, -0.97, 0.847, -0.637, 0.371, -0.07, -0.246, 0.533, -0.764, 0.927, -0.999, 0.966, -0.843,
                0.643, -0.371, 0.064, 0.243, -0.53, 0.77, -0.927, 0.993, -0.97, 0.847, -0.637, 0.371, -0.07, -0.246,
                0.533, -0.764, 0.927, -0.999};

        double[] result = flt.highPassFilter(this.signal2, 4, 40);
        Assertions.assertArrayEquals(result, out, 0.001);
    }

    @Test
    public void BandPassTest1() {
        final double[] out = {0.0, 0.002, 0.008, 0.01, -0.01, -0.047, -0.052, 0.015, 0.116, 0.152, 0.035, -0.189,
                -0.321, -0.167, 0.216, 0.509, 0.393, -0.125, -0.646, -0.677, -0.099, 0.648, 0.914, 0.418, -0.478,
                -1.031, -0.742, 0.181, 0.978,0.982, 0.182, -0.769, -1.09, -0.518, 0.465, 1.05, 0.772, -0.125, -0.903,
                -0.93, -0.198, 0.68, 0.984, 0.48, -0.411, -0.957, -0.708, 0.127, 0.853, 0.874, 0.174, -0.674, -0.969,
                -0.46, 0.434, 0.972, 0.709, -0.141, -0.883, -0.901, -0.172, 0.7, 0.996, 0.475, -0.438, -0.994, -0.73,
                0.137, 0.889, 0.908, 0.183, -0.692, -0.998, -0.479, 0.433, 0.983, 0.723, -0.131, -0.879, -0.902, -0.179,
                0.689, 0.987, 0.473, -0.43, -0.981, -0.721, 0.137, 0.88, 0.898, 0.178, -0.69, -0.992, -0.473, 0.436,
                0.983, 0.721, -0.134, -0.882, -0.904};

        double[] result = flt.bandPassFilter(this.signal1, 4, 12, 18);
        Assertions.assertArrayEquals(result, out, 0.001);
    }

    @Test
    public void BandPassTest2() {
        final double[] out = {0.0, 0.046, 0.078, -0.14, -0.365, 0.042, 0.726, 0.436, -0.711, -0.984, 0.14, 1.08, 0.565,
                -0.645, -0.943, 0.018, 0.93, 0.538, -0.617, -0.914, 0.08, 0.977, 0.527, -0.651, -0.938, 0.06, 0.97,
                0.544, -0.632, -0.928, 0.061, 0.966, 0.533, -0.639, -0.929, 0.064, 0.97, 0.536, -0.637, -0.931, 0.062,
                0.968, 0.536, -0.637, -0.93, 0.063, 0.968, 0.536, -0.638, -0.93, 0.062, 0.969, 0.536, -0.637, -0.929,
                0.062, 0.969, 0.535, -0.637, -0.93, 0.063, 0.969, 0.536, -0.637, -0.93, 0.063, 0.968, 0.537, -0.638,
                -0.929, 0.063, 0.969, 0.536, -0.638, -0.929, 0.062, 0.969, 0.536, -0.637, -0.931, 0.063, 0.969, 0.536,
                -0.637, -0.931, 0.063, 0.968, 0.536, -0.638, -0.93, 0.062, 0.969, 0.536, -0.638, -0.929, 0.062, 0.969,
                0.535, -0.637, -0.93};

        double[] result = flt.bandPassFilter(this.signal2, 4, 12, 30);
        Assertions.assertArrayEquals(result, out, 0.001);
    }

    @Test
    public void BandStopTest1() {
        final double[] out = {0.0, 0.311, -0.459, 0.841, 0.154, -0.143, 1.176, 0.738, 0.001, 1.273, 1.342, 0.023, 0.739,
                1.414, -0.128,-0.359, 0.745, -0.363, -1.447, -0.255, -0.37, -1.788, -0.881, 0.126, -1.036, -0.771, 0.92,
                0.372, -0.227,1.359, 1.494, 0.132, 0.907, 1.609, -0.01, -0.311, 0.766, -0.383, -1.512, -0.331, -0.434,
                -1.841, -0.917, 0.115, -1.025, -0.748, 0.949, 0.403, -0.202, 1.375, 1.499, 0.13, 0.898, 1.596, -0.023,
                -0.322, 0.758, -0.387, -1.512, -0.327, -0.429, -1.835, -0.912, 0.119, -1.023, -0.748, 0.947, 0.401,
                -0.204, 1.373, 1.498, 0.129, 0.897, 1.597, -0.022, -0.321, 0.759, -0.386, -1.511, -0.327, -0.429,
                -1.835, -0.912, 0.118, -1.024, -0.748, 0.947, 0.401, -0.204, 1.373, 1.498, 0.129, 0.898, 1.597, -0.022,
                -0.321, 0.759, -0.386, -1.511, -0.327};

        double[] result = flt.bandStopFilter(this.signal1, 4, 7, 28);
        Assertions.assertArrayEquals(result, out, 0.001);
    }

    @Test
    public void BandStopTest2() {
        final double[] out = {0.0, 0.285, -0.192, 0.881, -0.32, 1.281, -0.466, 1.531, -0.354, 1.314, 0.402, 0.963, 1.174,
                0.543, 1.781, 0.073, 1.968, -0.035, 1.758, 0.198, 1.217, 0.634, 0.421, 1.034, -0.34, 1.2, -0.83, 1.023,
                -0.94, 0.466, -0.721, -0.34, -0.337, -1.157, 0.005, -1.735, 0.111, -1.911, -0.108, -1.658, -0.599,
                -1.089, -1.179, -0.419, -1.616, 0.117, -1.713, 0.352, -1.386, 0.253, -0.696, -0.073, 0.167, -0.427,
                0.95, -0.592, 1.435, -0.428, 1.512, 0.075, 1.219, 0.785, 0.722, 1.477, 0.247, 1.915, -0.005, 1.941,
                0.061, 1.533, 0.399, 0.817, 0.839, 0.018, 1.159, -0.622, 1.167, -0.93, 0.785, -0.868, 0.077, -0.538,
                -0.763, -0.144, -1.487, 0.098, -1.879, 0.041, -1.835, -0.329, -1.401, -0.891, -0.751, -1.429, -0.122,
                -1.716, 0.276, -1.602, 0.34, -1.076};

        double[] result = flt.bandStopFilter(this.signal2, 4, 12, 30);
        Assertions.assertArrayEquals(result, out, 0.001);
    }

    @Test
    public void InterfaceTest1() {
        _IIRFilter interfaceFF = new Butterworth(100);
        final double[] out = {0.0, 0.285, -0.192, 0.881, -0.32, 1.281, -0.466, 1.531, -0.354, 1.314, 0.402, 0.963, 1.174,
                0.543, 1.781, 0.073, 1.968, -0.035, 1.758, 0.198, 1.217, 0.634, 0.421, 1.034, -0.34, 1.2, -0.83, 1.023,
                -0.94, 0.466, -0.721, -0.34, -0.337, -1.157, 0.005, -1.735, 0.111, -1.911, -0.108, -1.658, -0.599,
                -1.089, -1.179, -0.419, -1.616, 0.117, -1.713, 0.352, -1.386, 0.253, -0.696, -0.073, 0.167, -0.427,
                0.95, -0.592, 1.435, -0.428, 1.512, 0.075, 1.219, 0.785, 0.722, 1.477, 0.247, 1.915, -0.005, 1.941,
                0.061, 1.533, 0.399, 0.817, 0.839, 0.018, 1.159, -0.622, 1.167, -0.93, 0.785, -0.868, 0.077, -0.538,
                -0.763, -0.144, -1.487, 0.098, -1.879, 0.041, -1.835, -0.329, -1.401, -0.891, -0.751, -1.429, -0.122,
                -1.716, 0.276, -1.602, 0.34, -1.076};

        double[] result = interfaceFF.bandStopFilter(this.signal2, 4, 12, 30);
        Assertions.assertArrayEquals(result, out, 0.001);
    }

    @Test
    public void TestExceptions() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double[] result = flt.bandStopFilter(this.signal2, 4, 30, 12);;
        });
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            double[] result = flt.bandPassFilter(this.signal2, 4, 30, 12);;
        });
    }
}
