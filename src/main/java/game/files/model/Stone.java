package game.files.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Stone {

    private boolean isWhite;
    public boolean isPartOfGroup = false;

    public Stone() {

    }

    public Stone(boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isStoneWhite() {
        return this.isWhite;
    }
}
