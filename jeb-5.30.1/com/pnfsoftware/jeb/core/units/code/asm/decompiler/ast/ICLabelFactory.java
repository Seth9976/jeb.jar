package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Collection;

@Ser
public interface ICLabelFactory {
   ICLabel create(long var1, String var3);

   ICLabel create(long var1);

   ICLabel create();

   Collection getLabels();

   ICLabel get(String var1);

   ICLabel get(long var1);
}
