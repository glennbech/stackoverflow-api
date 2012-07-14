/*
 * I, the copyright holder of this work, hereby release it into the public domain. This applies worldwide.
 *
 * In case this is not legally possible, I grant any entity the right to use this work for any purpose,
 * without any conditions, unless such conditions are required by law.
 */

package com.stackoverflow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Glenn Bech
 */
public class PostList extends ArrayList<Post> {

    public PostList(List<Post> questions) {
        super(questions);
    }

    public PostList() {
    }

    public String getVectorizedSemiColonDelimeteredIdsFromQuestions() {
        Iterator<Post> i = iterator();
        String ids = "";
        while (i.hasNext()) {
            Post q = i.next();

            if (q instanceof Question) {
                ids += ((Question) (q)).getQuestionId();
            } else {
                ids += ((Answer) (q)).getAnswerId();
            }

            if (i.hasNext())
                ids += ";";
        }
        return ids;
    }


    public PostList changed(List<Question> compareTo) {

        PostList diff = new PostList();
        for (Post post : this) {
            int index = compareTo.indexOf(post);
            if (index == -1)
                continue;
            if (compareTo.get(index).getLastActivityDate() != post.getLastActivityDate()) {
                diff.add(compareTo.get(index));
            }
        }
        return diff;
    }
}
