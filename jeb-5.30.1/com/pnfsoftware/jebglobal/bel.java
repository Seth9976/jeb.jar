package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.AssetManager;
import com.pnfsoftware.jeb.core.output.IUnitFormatter;
import com.pnfsoftware.jeb.core.output.UnitFormatterUtil;
import com.pnfsoftware.jeb.core.properties.IPropertyDefinitionManager;
import com.pnfsoftware.jeb.core.units.AbstractUnit;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.IUnitProcessor;
import com.pnfsoftware.jeb.core.units.code.dart.IDartAotSnapshotInfo;
import com.pnfsoftware.jeb.core.units.code.dart.IDartAotUnit;
import com.pnfsoftware.jeb.core.units.code.dart.IDartInternalObject;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class bel extends AbstractUnit implements IDartAotUnit {
   @SerId(1)
   bel.CU q;
   @SerId(2)
   bel.CU RF;
   @SerId(3)
   String xK;
   @SerId(4)
   String Dw;
   @SerId(5)
   String Uv;

   public bel(bek var1, String var2, IUnitProcessor var3, IUnit var4, IPropertyDefinitionManager var5) {
      super("dart_aot_snapshot", var2, var3, var4, var5);
      this.q = new bel.CU(var1.RF);
      this.RF = new bel.CU(var1.xK);
      this.xK = var1.q(true, false, false, false);
      this.Dw = var1.q(false, false, true, false);
      this.Uv = var1.q(false, false, false, true);
   }

   @Override
   public IDartAotSnapshotInfo getVmSnapshotInfo() {
      return this.q;
   }

   @Override
   public IDartAotSnapshotInfo getIsolateSnapshotInfo() {
      return this.RF;
   }

   @Override
   protected boolean processInternal() {
      return true;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 2L) == null) {
         var1.addPresentation(new bem(this, 2L, S.L("Pool Information"), false), false);
      }

      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 3L) == null) {
         var1.addPresentation(new ben(this, 3L, S.L("Code Information"), false), false);
      }

      return var1;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append("\n");
      var1.append(this.xK);
      return var1.toString();
   }

   @Override
   public byte[] getIconData() {
      return AssetManager.getAssetBytes("icons/dart_icon.png");
   }

   @Override
   public List getInternalObjects() {
      return Collections.unmodifiableList(this.RF.gO);
   }

   @Override
   public List generatePrimaryPool() {
      ArrayList var1 = new ArrayList();

      for (bel.eo var3 : this.RF.gO) {
         if ("kObjectPoolCid".equals(this.RF.nf.get(var3.getClassId())) && var3.getAttribute("data") != null) {
            var1.add(null);
            List var4 = (List)var3.getAttribute("data");
            if (var4 == null) {
               break;
            }

            for (Object var6 : var4) {
               Map var7 = (Map)var6;
               Object var8 = var7.get("rawObj");
               if (var8 instanceof Long) {
                  bel.eo var9 = (bel.eo)this.RF.gO.get(((Long)var8).intValue());
                  var1.add(var9);
               } else {
                  var8 = var7.get("rawValue");
                  if (var8 instanceof Long) {
                     var1.add(var8);
                  } else {
                     var1.add(null);
                  }
               }
            }
         }
      }

      return var1;
   }

   @Ser
   public static class CU implements IDartAotSnapshotInfo {
      @SerId(1)
      String q;
      @SerId(2)
      byte[] RF;
      @SerId(3)
      long xK;
      @SerId(4)
      Set Dw;
      @SerId(5)
      long Uv;
      @SerId(6)
      long oW;
      @SerId(7)
      List gO;
      @SerId(8)
      Map nf;

      public CU(bex var1) {
         this.q = var1.xK;
         this.RF = var1.zz;
         this.xK = var1.za;
         this.Dw = var1.JY;
         this.Uv = var1.If;
         this.oW = var1.Dz;
         this.gO = new ArrayList(var1.mI.size());
         var1.mI.forEach(var1x -> this.gO.add(new bel.eo(var1x)));
         this.nf = var1.KT();
      }

      @Override
      public String getVersionTag() {
         return this.q;
      }

      @Override
      public String getVersionHash() {
         return Formatter.byteArrayToHex(this.RF).toString();
      }

      @Override
      public long getSnapshotSize() {
         return this.xK;
      }

      @Override
      public Set getFeatures() {
         return Collections.unmodifiableSet(this.Dw);
      }

      @Override
      public long getBaseObjectsCount() {
         return this.Uv;
      }

      @Override
      public long getObjectsCount() {
         return this.oW;
      }

      @Override
      public List getInternalObjects() {
         return Collections.unmodifiableList(this.gO);
      }

      @Override
      public Map getClassIdNameMap() {
         return Collections.unmodifiableMap(this.nf);
      }

      @Override
      public String toString() {
         return Strings.ff(
            "Snapshot: size=%d, objectCount=%d, versionTag=%s, versionHash=%s, features=%s",
            this.getSnapshotSize(),
            this.getObjectsCount(),
            this.getVersionTag(),
            this.getVersionHash(),
            this.getFeatures()
         );
      }
   }

   @Ser
   public static class eo implements IDartInternalObject {
      @SerId(1)
      int q;
      @SerId(2)
      boolean RF;
      @SerId(3)
      Map xK;

      public eo(bew var1) {
         this.q = var1.RF;
         this.RF = var1.xK;
         this.xK = var1.Dw;
      }

      @Override
      public int getClassId() {
         return this.q;
      }

      @Override
      public boolean isBase() {
         return this.RF;
      }

      @Override
      public Map getAttributes() {
         return Collections.unmodifiableMap(this.xK);
      }

      @Override
      public Object getAttribute(String var1) {
         return this.xK.get(var1);
      }

      @Override
      public String toString() {
         return Strings.ff("Object: cid=%d, attrmap=%s", this.q, this.xK);
      }
   }
}
