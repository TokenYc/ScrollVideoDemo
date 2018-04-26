package net.archeryc.scrollvideodemo;

/**
 * @author ArcherYc
 * @date on 2018/4/20  上午9:05
 * @mail 247067345@qq.com
 */
public class VideoEntity {
    private String cover;
    private String video;

    public VideoEntity(String cover, String video) {
        this.cover = cover;
        this.video = video;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
