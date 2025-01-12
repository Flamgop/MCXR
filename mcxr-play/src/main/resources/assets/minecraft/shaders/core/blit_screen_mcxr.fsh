#version 150

uniform sampler2D DiffuseSampler;

uniform vec4 ColorModulator;

in vec2 texCoord;
in vec4 vertexColor;

out vec4 fragColor;

float sRGBToLinear(float f) {
    if (f < 0.04045f) {
        return f / 12.92f;
    } else {
        return pow((f + 0.055f) / 1.055f, 2.4f);
    }
}

void main() {
    //TODO use a more modern aa alg (smaa?)
    //TODO make aa configuarable
    vec4 color = texture(DiffuseSampler, texCoord) * vertexColor;
    vec4 mcColor = color * ColorModulator;

    // blit final output of compositor into displayed back buffer
    // apply inverse gamma correction since minecraft renders in sRGB space but we want our output to be linear
    gl_FragColor = vec4(sRGBToLinear(mcColor.r), sRGBToLinear(mcColor.g), sRGBToLinear(mcColor.b), mcColor.a);
}