package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.emulator;

import com.pnfsoftware.jeb.core.units.IPriorityBasedHooks;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import java.util.List;

public interface IEEmulatorHooks extends IPriorityBasedHooks {
   default Boolean evaluateAt(EEmulator var1, long var2, IInstruction var4) {
      return null;
   }

   default void postEvaluateAt(EEmulator var1, long var2, IInstruction var4, long var5, boolean var7) {
   }

   default Long evaluateSyscall(EEmulator var1, long var2, IInstruction var4, int var5, String var6, INativeMethodItem var7, List var8) {
      return null;
   }

   default void postEvaluateSyscall(
      EEmulator var1, long var2, IInstruction var4, int var5, String var6, INativeMethodItem var7, List var8, long var9, long var11
   ) {
   }

   default Boolean evaluateExternal(EEmulator var1, String var2, INativeMethodItem var3) {
      return null;
   }

   default void postEvaluateExternal(EEmulator var1, String var2, INativeMethodItem var3, long var4, boolean var6) {
   }

   default Boolean evaluateUntranslated(EEmulator var1, IEUntranslatedInstruction var2, IInstruction var3) {
      return null;
   }

   default void postEvaluateUntranslated(EEmulator var1, IEUntranslatedInstruction var2, IInstruction var3, long var4, Boolean var6) {
   }

   default void monitorHLSpecial(EEmulator var1, int var2, List var3) {
   }
}
