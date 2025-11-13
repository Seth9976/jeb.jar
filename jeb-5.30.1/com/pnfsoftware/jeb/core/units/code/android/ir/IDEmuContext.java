package com.pnfsoftware.jeb.core.units.code.android.ir;

import java.util.Collection;

public interface IDEmuContext {
   IDEmuContext copy();

   String getName();

   boolean hasFrames();

   int getCountOfFrames();

   Collection getFrames();

   IDEmuFrame getFrame(int var1);

   Collection getOrigins();

   void pushOriginInfo(String var1);
}
