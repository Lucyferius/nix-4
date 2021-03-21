package nix.alevel;

public interface StringReverseService {
    String reverse(String src);
    String reverse(String src, String dest);
    String reverse(String src, int firstIndex, int lastIndex);
}
