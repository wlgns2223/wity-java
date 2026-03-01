package im.wity.dto.block;

public record Size(int width,int height) {
    public static Size from(int width,int height){
        return new Size(width, height);
    }
    public static Size from (){
        return new Size(0, 0);
    }
}
