package net.archeryc.scrollvideodemo.widget.QfVideo;

import android.util.Pair;

/**
 * @author ArcherYc
 * @date on 2018/4/24  下午3:44
 * @mail 247067345@qq.com
 */
public class VideoUtils {
    public static Pair<Integer, Integer> getFitSize(Pair<Integer, Integer> layoutSize,
                                                    Pair<Integer, Integer> videoSize) {
        int layoutWidth = layoutSize.first;
        int layoutHeight = layoutSize.second;
        int videoWidth = videoSize.first;
        int videoHeight = videoSize.second;

        float layoutAspectRatio = (float) layoutWidth / (float) layoutHeight;
        float videoAspectRatio = (float) videoWidth / (float) videoHeight;

        if (layoutAspectRatio >= videoAspectRatio) {
            int fitHeight = layoutHeight;
            int fitWidth = (int) (fitHeight * videoAspectRatio);
            return new Pair<>(fitWidth, fitHeight);
        } else {
            int fitWidth = layoutWidth;
            int fitHeight = (int) (layoutWidth / videoAspectRatio);
            return new Pair<>(fitWidth, fitHeight);
        }
    }
}
