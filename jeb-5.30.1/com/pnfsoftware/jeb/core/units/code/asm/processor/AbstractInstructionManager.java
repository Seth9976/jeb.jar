package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.util.collect.CFBytesTrie;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public abstract class AbstractInstructionManager implements CFBytesTrie.IKeyExtractor {
   private CFBytesTrie insnCacheFast = new CFBytesTrie();

   public AbstractInstructionManager() {
      this.insnCacheFast.setKeyExtractor(this);
   }

   public IInstruction getInstruction(BytesBlock var1) throws ProcessorException {
      boolean var2 = this.useCache(var1);
      byte[] var3 = this.buildKey(var1);
      if (var2) {
         IInstruction var4 = (IInstruction)this.insnCacheFast.get(var3, false);
         if (var4 != null) {
            return this.retrieveInstructionFromCache(var4);
         }
      }

      try {
         IInstruction var7 = this.findInstruction(var1);
         if (var7 != null && var2) {
            this.insnCacheFast.put(var3, var7);
         }

         return var7;
      } catch (ProcessorException var6) {
         throw var6;
      }
   }

   protected IInstruction retrieveInstructionFromCache(IInstruction var1) {
      return var1;
   }

   protected abstract IInstruction findInstruction(BytesBlock var1) throws ProcessorException;

   protected boolean useCache(BytesBlock var1) {
      return true;
   }

   protected byte[] buildKey(BytesBlock var1) {
      return var1.getCode();
   }

   protected static void raiseUndefined(byte[] var0) throws ProcessorException {
      throw new ProcessorException(getUndefinedMessage(var0));
   }

   protected static String getUndefinedMessage(byte[] var0) {
      return Strings.f("Instruction %s is undefined", Formatter.byteArrayToHex(var0));
   }

   protected static String getUnpredictableMessage(byte[] var0, String var1) {
      return Strings.f("Instruction %s %s is unpredictable", Formatter.byteArrayToHex(var0), var1);
   }

   public byte[] extract(IInstruction var1) {
      return var1.getCode();
   }

   public void clearCache() {
      this.insnCacheFast.clear();
   }
}
