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
public class bas extends AbstractUnit implements IDartAotUnit {
   @SerId(1)
   bas.Sv pC;
   @SerId(2)
   bas.Sv A;
   @SerId(3)
   String kS;
   @SerId(4)
   String wS;
   @SerId(5)
   String UT;

   public bas(bar var1, String var2, IUnitProcessor var3, IUnit var4, IPropertyDefinitionManager var5) {
      super("dart_aot_snapshot", var2, var3, var4, var5);
      this.pC = new bas.Sv(var1.pC);
      this.A = new bas.Sv(var1.A);
      this.kS = var1.pC(true, false, false, false);
      this.wS = var1.pC(false, false, true, false);
      this.UT = var1.pC(false, false, false, true);
   }

   @Override
   public IDartAotSnapshotInfo getVmSnapshotInfo() {
      return this.pC;
   }

   @Override
   public IDartAotSnapshotInfo getIsolateSnapshotInfo() {
      return this.A;
   }

   @Override
   protected boolean processInternal() {
      return true;
   }

   @Override
   public IUnitFormatter getFormatter() {
      IUnitFormatter var1 = super.getFormatter();
      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 2L) == null) {
         var1.addPresentation(new bat(this, 2L, S.L("Pool Information"), false), false);
      }

      if (UnitFormatterUtil.getPresentationByIdentifier(var1, 3L) == null) {
         var1.addPresentation(new bau(this, 3L, S.L("Code Information"), false), false);
      }

      return var1;
   }

   @Override
   public String getDescription() {
      StringBuilder var1 = new StringBuilder(super.getDescription());
      var1.append("\n");
      var1.append(this.kS);
      return var1.toString();
   }

   @Override
   public byte[] getIconData() {
      return AssetManager.getAssetBytes("icons/dart_icon.png");
   }

   @Override
   public List getInternalObjects() {
      return Collections.unmodifiableList(this.A.sY);
   }

   @Override
   public List generatePrimaryPool() {
      ArrayList var1 = new ArrayList();

      for (bas.Av var3 : this.A.sY) {
         if ("kObjectPoolCid".equals(this.A.ys.get(var3.getClassId())) && var3.getAttribute("data") != null) {
            var1.add(null);
            List var4 = (List)var3.getAttribute("data");
            if (var4 == null) {
               break;
            }

            for (Object var6 : var4) {
               Map var7 = (Map)var6;
               Object var8 = var7.get("rawObj");
               if (var8 instanceof Long) {
                  bas.Av var9 = (bas.Av)this.A.sY.get(((Long)var8).intValue());
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
   public static class Av implements IDartInternalObject {
      @SerId(1)
      int pC;
      @SerId(2)
      boolean A;
      @SerId(3)
      Map kS;

      public Av(bbc var1) {
         this.pC = var1.A;
         this.A = var1.kS;
         this.kS = var1.wS;
      }

      @Override
      public int getClassId() {
         return this.pC;
      }

      @Override
      public boolean isBase() {
         return this.A;
      }

      @Override
      public Map getAttributes() {
         return Collections.unmodifiableMap(this.kS);
      }

      @Override
      public Object getAttribute(String var1) {
         return this.kS.get(var1);
      }

      @Override
      public String toString() {
         return Strings.ff("Object: cid=%d, attrmap=%s", this.pC, this.kS);
      }
   }

   @Ser
   public static class Sv implements IDartAotSnapshotInfo {
      @SerId(1)
      String pC;
      @SerId(2)
      byte[] A;
      @SerId(3)
      long kS;
      @SerId(4)
      Set wS;
      @SerId(5)
      long UT;
      @SerId(6)
      long E;
      @SerId(7)
      List sY;
      @SerId(8)
      Map ys;

      public Sv(bbd var1) {
         this.pC = var1.A;
         this.A = var1.oT;
         this.kS = var1.ld;
         this.wS = var1.fI;
         this.UT = var1.Aj;
         this.E = var1.EX;
         this.sY = new ArrayList(var1.LM.size());
         var1.LM.forEach(var1x -> this.sY.add(new bas.Av(var1x)));
         this.ys = var1.ah();
      }

      @Override
      public String getVersionTag() {
         return this.pC;
      }

      @Override
      public String getVersionHash() {
         return Formatter.byteArrayToHex(this.A).toString();
      }

      @Override
      public long getSnapshotSize() {
         return this.kS;
      }

      @Override
      public Set getFeatures() {
         return Collections.unmodifiableSet(this.wS);
      }

      @Override
      public long getBaseObjectsCount() {
         return this.UT;
      }

      @Override
      public long getObjectsCount() {
         return this.E;
      }

      @Override
      public List getInternalObjects() {
         return Collections.unmodifiableList(this.sY);
      }

      @Override
      public Map getClassIdNameMap() {
         return Collections.unmodifiableMap(this.ys);
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
}
