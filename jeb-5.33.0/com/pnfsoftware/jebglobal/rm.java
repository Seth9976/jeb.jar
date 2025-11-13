package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.IArtifact;
import com.pnfsoftware.jeb.core.ILiveArtifact;
import com.pnfsoftware.jeb.core.IRuntimeProject;
import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class rm implements ILiveArtifact, wq {
   private static final ILogger pC = GlobalLog.getLogger(rm.class);
   @SerId(1)
   private wq.Av A = wq.Av.pC;
   @SerId(2)
   private Or kS;
   @SerId(3)
   private IArtifact wS;
   @SerId(4)
   private List UT;

   rm(Or var1, IArtifact var2) {
      this.kS = var1;
      this.wS = var2;
      this.UT = new ArrayList();
   }

   @Override
   public IRuntimeProject getRuntimeProject() {
      return this.kS;
   }

   @Override
   public IArtifact getArtifact() {
      return this.wS;
   }

   @Override
   public List getUnits() {
      synchronized (this.UT) {
         return new ArrayList(this.UT);
      }
   }

   @Override
   public IUnit getMainUnit() {
      synchronized (this.UT) {
         return this.UT.isEmpty() ? null : (IUnit)this.UT.get(0);
      }
   }

   public boolean pC(IUnit var1) {
      synchronized (this.UT) {
         if (!this.UT.contains(var1)) {
            return false;
         }

         var1.dispose();
         this.UT.remove(var1);
      }

      this.kS.notifyListeners(new JebEvent(J.UnitDestroyed, var1));
      return true;
   }

   @Override
   public boolean load(String var1, boolean var2, boolean var3) {
      synchronized (this) {
         if (this.A != wq.Av.pC) {
            throw new IllegalStateException();
         }

         this.A = wq.Av.A;
         Object[] var10000 = new Object[0];
      }

      int var4 = this.kS.getPropertyManager().getInteger(".project.ArtifactProcessingDepth");
      IUnitProcessor var5 = this.kS.getProcessor();
      int var6 = var5.setProcessingDepth(var4);
      IUnit var7 = var5.process(this.wS.getName(), this.wS.getInput(), this.wS, var1, var2, var3);
      var5.setProcessingDepth(var6);
      if (var7 == null) {
         Object[] var13 = new Object[0];
         this.A = wq.Av.wS;
         return false;
      } else {
         synchronized (this.UT) {
            this.UT.add(var7);
         }

         this.A = wq.Av.kS;
         Object[] var12 = new Object[0];
         this.kS.notifyListeners(new JebEvent(J.UnitCreated, var7));
         return true;
      }
   }

   @Override
   public String toString() {
      return this.wS.toString();
   }
}
