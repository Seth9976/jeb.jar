package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.codeobject.CompilerType;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jebglobal.abd;
import com.pnfsoftware.jebglobal.abe;
import com.pnfsoftware.jebglobal.abf;
import com.pnfsoftware.jebglobal.abg;
import com.pnfsoftware.jebglobal.abh;
import com.pnfsoftware.jebglobal.abi;
import com.pnfsoftware.jebglobal.abj;
import com.pnfsoftware.jebglobal.abk;
import com.pnfsoftware.jebglobal.abl;

@Ser
public interface ICompiler {
   ICompiler COMP_UNKNOWN = new abi();
   ICompiler COMP_UNKNOWN_LINUX = new abj();
   ICompiler COMP_UNKNOWN_WINDOWS = new abk();
   ICompiler COMP_ANDROID_ART = new abd();
   ICompiler COMP_ANDROID_NDK = new abe();
   ICompiler COMP_VISUAL_STUDIO = new abl();

   String getName();

   CompilerType getType();

   default boolean isUnknown() {
      return this instanceof abi;
   }

   default boolean isWindowsCompatible() {
      return this instanceof abh;
   }

   default boolean isVisualStudio() {
      return this instanceof abl;
   }

   default boolean isLinuxCompatible() {
      return this instanceof abg;
   }

   default boolean isAndroidCompatible() {
      return this instanceof abf;
   }

   default boolean isAndroidART() {
      return this instanceof abd;
   }

   default boolean isAndroidNDK() {
      return this instanceof abe;
   }

   int getPropertyId();
}
