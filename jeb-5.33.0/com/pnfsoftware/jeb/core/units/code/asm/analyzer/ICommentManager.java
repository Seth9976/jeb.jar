package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Map;

@Ser
public interface ICommentManager {
   Map getComments();

   String getComment(long var1);

   boolean setComment(long var1, String var3);

   boolean addComment(long var1, String var3);
}
