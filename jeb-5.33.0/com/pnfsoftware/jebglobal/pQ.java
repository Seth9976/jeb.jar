package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationApplicator;
import com.pnfsoftware.jeb.core.units.codeobject.ELFRelocationContext;
import com.pnfsoftware.jeb.core.units.codeobject.ELFStandardRelocOperations;
import com.pnfsoftware.jeb.core.units.codeobject.IELFArchRelocatorFacility;
import com.pnfsoftware.jeb.core.units.codeobject.IELFRelocationApplicator;
import java.util.Arrays;
import java.util.List;

public class pQ implements IELFArchRelocatorFacility {
   public static pQ.Sv pC = new WK(0);
   public static pQ.Sv A = new JS(256);
   public static pQ.Sv kS = new qJ(1027);
   public static pQ.Sv wS = new CK(1032);
   public static pQ.Sv UT = new HO(257);
   public static pQ.Sv E = new UQ(258);
   public static pQ.Sv sY = new of(261);
   public static pQ.Sv ys = new qd(260);
   public static pQ.Sv ld = new DO(283);
   public static pQ.Sv gp = new F(282);
   public static pQ.Sv oT = new cn(275);
   public static pQ.Sv fI = new Wc(277);
   public static pQ.Sv WR = new CF(1025);
   public static pQ.Sv NS = new Tv(1026);
   public static pQ.Sv vP = new Ty(1024);
   public static ELFRelocationContext xC = new iP(pC, A, UT, E, sY, ys, ld, gp, oT, fI);
   public static ELFRelocationContext ED = new OB(pC, A, wS, WR, NS, UT);
   public static ELFRelocationContext Sc = new rt(kS, vP);
   public static ELFRelocationContext ah = new iH(UT, kS);

   protected static void pC(pQ.Av var0, int var1) throws MemoryException {
      int var2 = var0.mem.readInt(var0.P());
      var0.mem.writeInt(var0.P(), var2 | var1);
   }

   protected static void pC(pQ.Av var0, long var1) throws MemoryException {
      long var3 = var0.mem.readLong(var0.P());
      var0.mem.writeLong(var0.P(), var3 | var1);
   }

   protected static void A(pQ.Av var0, int var1) throws MemoryException {
      int var2 = var0.mem.readInt(var0.P());
      var0.mem.writeInt(var0.P(), var2 + var1);
   }

   protected static void A(pQ.Av var0, long var1) throws MemoryException {
      long var3 = var0.mem.readLong(var0.P());
      var0.mem.writeLong(var0.P(), var3 + var1);
   }

   protected static void pC(pQ.Av var0) throws MemoryException {
      long var1 = var0.S() + var0.A() - var0.P();
      var1 = (var1 & 268435452L) >> 2;
      pC(var0, (int)var1);
   }

   @Override
   public List getRelocationContexts() {
      return Arrays.asList(xC, ED, Sc, ah);
   }

   static class Av extends ELFStandardRelocOperations {
      Av(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) {
         super(var1, var2);
      }

      @Override
      public long A() throws MemoryException {
         return this.relocEntry.isAddendSet() ? this.relocEntry.getAddend() : this.mem.readLong(this.targetAddress);
      }
   }

   abstract static class Sv extends ELFRelocationApplicator {
      public Sv(int var1) {
         super(var1);
      }

      @Override
      public void apply(IELFRelocationApplicator.RelocatedFile var1, IELFRelocationApplicator.RelocInstance var2) throws MemoryException {
         pQ.Av var3 = new pQ.Av(var1, var2);
         this.applyInternal(var3);
      }
   }
}
