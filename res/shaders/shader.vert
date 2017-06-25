#version 400

in vec2 position;
in vec2 textureCoordinates;

uniform mat4 pr_matrix;
uniform mat4 trans_matrix;

out vec2 pass_tc;


vec2 rotate(vec2 v, float a){
    float s = sin(a);
    float c = cos(a);
    mat2 m = mat2(c, -s, s, c);
    return m * v;
}

void main(void){

    gl_Position = pr_matrix * trans_matrix * vec4(position, 0, 1);
    pass_tc = textureCoordinates;


}