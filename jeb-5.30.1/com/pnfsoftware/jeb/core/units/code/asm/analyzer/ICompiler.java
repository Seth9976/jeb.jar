package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jebglobal.acv;
import com.pnfsoftware.jebglobal.acw;
import com.pnfsoftware.jebglobal.acx;
import com.pnfsoftware.jebglobal.acy;
import com.pnfsoftware.jebglobal.acz;
import com.pnfsoftware.jebglobal.ada;
import com.pnfsoftware.jebglobal.adb;
import com.pnfsoftware.jebglobal.adc;
import com.pnfsoftware.jebglobal.add;

@Ser
public interface ICompiler {
   ICompiler COMP_UNKNOWN = new ada();
   ICompiler COMP_UNKNOWN_LINUX = new adb();
   ICompiler COMP_UNKNOWN_WINDOWS = new adc();
   ICompiler COMP_ANDROID_ART = new acv();
   ICompiler COMP_ANDROID_NDK = new acw();
   ICompiler COMP_VISUAL_STUDIO = new add();

   String getName();

   CompilerType getType();

   default boolean isUnknown() {
      return this instanceof ada;
   }

   default boolean isWindowsCompatible() {
      return this instanceof acz;
   }

   default boolean isVisualStudio() {
      return this instanceof add;
   }

   default boolean isLinuxCompatible() {
      return this instanceof acy;
   }

   default boolean isAndroidCompatible() {
      return this instanceof acx;
   }

   default boolean isAndroidART() {
      return this instanceof acv;
   }

   default boolean isAndroidNDK() {
      return this instanceof acw;
   }

   int getPropertyId();
}
