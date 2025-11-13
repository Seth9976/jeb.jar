package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.List;

@Ser
public interface ICallGraph {
   void recordInternalCall(long var1, CodePointer var3, boolean var4);

   void recordExternalCall(long var1, INativeMethodItem var3, boolean var4);

   void recordUnresolvedCall(long var1, long var3, boolean var5);

   void removeCallRelationship(long var1);

   List getCallees(long var1, boolean var3);

   List getCallees(INativeMethodItem var1, boolean var2);

   List getCalleeRoutines(long var1, boolean var3);

   List getCallers(CallGraphVertex var1, boolean var2);

   List getCallers(INativeMethodItem var1, boolean var2);

   List getCallerRoutines(CodePointer var1, boolean var2);
}
