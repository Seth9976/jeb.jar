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
public class ry implements ILiveArtifact, ME {
   private static final ILogger q = GlobalLog.getLogger(ry.class);
   @SerId(1)
   private ME.eo RF = ME.eo.q;
   @SerId(2)
   private Pl xK;
   @SerId(3)
   private IArtifact Dw;
   @SerId(4)
   private List Uv;

   ry(Pl var1, IArtifact var2) {
      this.xK = var1;
      this.Dw = var2;
      this.Uv = new ArrayList();
   }

   @Override
   public IRuntimeProject getRuntimeProject() {
      return this.xK;
   }

   @Override
   public IArtifact getArtifact() {
      return this.Dw;
   }

   @Override
   public List getUnits() {
      synchronized (this.Uv) {
         return new ArrayList(this.Uv);
      }
   }

   @Override
   public IUnit getMainUnit() {
      synchronized (this.Uv) {
         return this.Uv.isEmpty() ? null : (IUnit)this.Uv.get(0);
      }
   }

   public boolean q(IUnit var1) {
      synchronized (this.Uv) {
         if (!this.Uv.contains(var1)) {
            return false;
         }

         var1.dispose();
         this.Uv.remove(var1);
      }

      this.xK.notifyListeners(new JebEvent(J.UnitDestroyed, var1));
      return true;
   }

   @Override
   public ME.eo q() {
      return this.RF;
   }

   @Override
   public boolean RF() {
      return this.load(null, false, false);
   }

   @Override
   public boolean load(String var1, boolean var2, boolean var3) {
      synchronized (this) {
         if (this.RF != ME.eo.q) {
            throw new IllegalStateException();
         }

         this.RF = ME.eo.RF;
         Object[] var10000 = new Object[0];
      }

      int var4 = this.xK.getPropertyManager().getInteger(".project.ArtifactProcessingDepth");
      IUnitProcessor var5 = this.xK.getProcessor();
      int var6 = var5.setProcessingDepth(var4);
      IUnit var7 = var5.process(this.Dw.getName(), this.Dw.getInput(), this.Dw, var1, var2, var3);
      var5.setProcessingDepth(var6);
      if (var7 == null) {
         Object[] var13 = new Object[0];
         this.RF = ME.eo.Dw;
         return false;
      } else {
         synchronized (this.Uv) {
            this.Uv.add(var7);
         }

         this.RF = ME.eo.xK;
         Object[] var12 = new Object[0];
         this.xK.notifyListeners(new JebEvent(J.UnitCreated, var7));
         return true;
      }
   }

   @Override
   public String toString() {
      return this.Dw.toString();
   }
}
