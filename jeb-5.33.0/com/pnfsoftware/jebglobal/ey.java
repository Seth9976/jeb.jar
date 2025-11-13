package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IMemoryModel;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryModelEventType;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceLocation;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collections;
import java.util.Set;

@Ser
public class ey implements IReferenceManager {
   private static final ILogger A = GlobalLog.getLogger(ey.class);
   @SerId(1)
   private IMemoryModel kS;
   @SerId(2)
   acb pC;

   public ey(IMemoryModel var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.kS = var1;
         this.pC = new acb();
      }
   }

   @Override
   public boolean recordInternalReference(long var1, long var3, ReferenceType var5) {
      return this.recordInternalReference(var1, var3, var5, 0);
   }

   @Override
   public boolean recordInternalReference(long var1, long var3, ReferenceType var5, int var6) {
      return this.pC(ReferenceLocation.create(var1), ReferenceLocation.create(var3), var5, var6);
   }

   @Override
   public boolean recordExternalReference(long var1, INativeMethodItem var3, ReferenceType var4) {
      return this.recordExternalReference(var1, var3, var4, 0);
   }

   @Override
   public boolean recordExternalReference(long var1, INativeMethodItem var3, ReferenceType var4, int var5) {
      return this.pC(ReferenceLocation.create(var1), ReferenceLocation.createFromExternalRoutine(var3), var4, var5);
   }

   @Override
   public boolean recordReference(ReferenceLocation var1, ReferenceLocation var2, ReferenceType var3) {
      return this.pC(var1, var2, var3, 0);
   }

   @Override
   public boolean recordReference(ReferenceLocation var1, ReferenceLocation var2, ReferenceType var3, int var4) {
      return this.pC(var1, var2, var3, var4);
   }

   private boolean pC(ReferenceLocation var1, ReferenceLocation var2, ReferenceType var3, int var4) {
      try (ACLock var5 = this.kS.getLock().a()) {
         xy var6 = new xy(var1, var2);
         var6.pC(var3);
         var6.pC(var4);
         boolean var7 = this.pC.pC((aca)var6);
         if (var7) {
            if (var2.isInternalAddress()) {
               this.kS.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var2.getInternalAddress());
            }

            if (var1.isInternalAddress()) {
               this.kS.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var1.getInternalAddress());
            }

            return true;
         }
      }

      return false;
   }

   @Override
   public Set getReferencesTo(long var1) {
      return this.getReferencesTo(ReferenceLocation.create(var1));
   }

   @Override
   public Set getReferencesToExternalMethod(INativeMethodItem var1) {
      return this.getReferencesTo(ReferenceLocation.createFromExternalRoutine(var1));
   }

   @Override
   public Set getReferencesTo(ReferenceLocation var1) {
      Set var2 = this.pC.kS(var1);
      return var2 == null ? Collections.emptySet() : var2;
   }

   @Override
   public Set getReferencesFrom(long var1) {
      return this.getReferencesFrom(ReferenceLocation.create(var1));
   }

   @Override
   public Set getReferencesFrom(ReferenceLocation var1) {
      Set var2 = this.pC.A(var1);
      return var2 == null ? Collections.emptySet() : var2;
   }

   @Override
   public boolean unrecordAllReferencesFrom(long var1) {
      return this.unrecordAllReferencesFrom(ReferenceLocation.create(var1));
   }

   @Override
   public boolean unrecordAllReferencesFrom(ReferenceLocation var1) {
      try (ACLock var2 = this.kS.getLock().a()) {
         if (this.pC.sY(var1)) {
            if (var1.isInternalAddress()) {
               this.kS.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var1.getInternalAddress());
            }

            return true;
         }
      }

      return false;
   }

   @Override
   public boolean unrecordAllReferencesTo(long var1) {
      return this.unrecordAllReferencesTo(ReferenceLocation.create(var1));
   }

   @Override
   public boolean unrecordAllReferencesTo(ReferenceLocation var1) {
      try (ACLock var2 = this.kS.getLock().a()) {
         if (this.pC.ys(var1)) {
            if (var1.isInternalAddress()) {
               this.kS.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var1.getInternalAddress());
            }

            return true;
         }
      }

      return false;
   }

   @Override
   public boolean unrecordReference(long var1, long var3) {
      return this.unrecordReference(ReferenceLocation.create(var1), ReferenceLocation.create(var3));
   }

   @Override
   public boolean unrecordReference(ReferenceLocation var1, ReferenceLocation var2) {
      try (ACLock var3 = this.kS.getLock().a()) {
         if (this.pC.pC(var1, var2)) {
            if (var1.isInternalAddress()) {
               this.kS.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var1.getInternalAddress());
            }

            if (var2.isInternalAddress()) {
               this.kS.notifyListenersOfModelChange(MemoryModelEventType.REFERENCE_UPDATE, var2.getInternalAddress());
            }

            return true;
         }
      }

      return false;
   }
}
