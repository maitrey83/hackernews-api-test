package hackernews;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private Integer id;
    private String type;
    private String by;
    private Long time;
    private String text;
    private String title;
    private String url;
    private Integer score;
    private Integer parent;
    private List<Integer> kids;
    private Integer descendants;
    private Boolean deleted;
    private Boolean dead;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getBy() { return by; }
    public void setBy(String by) { this.by = by; }

    public Long getTime() { return time; }
    public void setTime(Long time) { this.time = time; }

    public String getText() { return text; }
    public void setText(String text) { this.text = text; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    public Integer getParent() { return parent; }
    public void setParent(Integer parent) { this.parent = parent; }

    public List<Integer> getKids() { return kids; }
    public void setKids(List<Integer> kids) { this.kids = kids; }

    public Integer getDescendants() { return descendants; }
    public void setDescendants(Integer descendants) { this.descendants = descendants; }

    public Boolean getDeleted() { return deleted; }
    public void setDeleted(Boolean deleted) { this.deleted = deleted; }

    public Boolean getDead() { return dead; }
    public void setDead(Boolean dead) { this.dead = dead; }

    public boolean hasComments() {
        return kids != null && !kids.isEmpty();
    }
}
