package com.pnfsoftware.jeb.corei.parsers.dex;

import com.pnfsoftware.jeb.core.BookmarkManager;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.IdentifierCoordinates;
import com.pnfsoftware.jeb.core.units.IQuickStateObject;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.android.DexCommentManager;
import com.pnfsoftware.jeb.core.units.impl.Comment;
import com.pnfsoftware.jeb.core.units.impl.MetaComment;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerVersion;
import com.pnfsoftware.jebglobal.bfs;
import com.pnfsoftware.jebglobal.bft;
import com.pnfsoftware.jebglobal.bfu;
import com.pnfsoftware.jebglobal.bgj;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

@Ser
@SerVersion(1)
public class oP implements IQuickStateObject {
   private static final ILogger pC = GlobalLog.getLogger(oP.class);
   @SerId(1)
   private int A;
   @SerId(2)
   private Map kS;
   @SerId(3)
   private Map wS;
   @SerId(4)
   private Map UT;
   @SerId(5)
   private Map E;
   @SerId(6)
   private Map sY;
   @SerId(7)
   private Map ys;
   @SerId(value = 8, version = 1)
   private Map ld;
   @SerId(value = 9, version = 1)
   private vi.Av gp;
   @SerId(10)
   private Map oT;
   @SerId(11)
   private Map fI;

   public oP(vi var1) throws IOException {
      this.A = Hash.calculateCRC32(var1.E());
      DexCommentManager var2 = var1.getCommentManager();
      this.oT = new HashMap(var2.getComments2());
      this.wS = new HashMap(var1.getAddressLabels());
      this.UT = new LinkedHashMap();

      for (qt var4 : var1.getPackages()) {
         if (var4.isRenamed()) {
            this.UT.put(var4.getSignature(false), var4.getName(true));
         }
      }

      this.E = new LinkedHashMap();

      for (bfs var9 : var1.getClasses()) {
         if (var9.isRenamed()) {
            this.E.put(var9.getSignature(false), var9.getName(true));
         }
      }

      this.sY = new LinkedHashMap();

      for (bft var10 : var1.getFields()) {
         if (var10.isRenamed()) {
            this.sY.put(var10.getSignature(false), var10.getName(true));
         }
      }

      this.ys = new LinkedHashMap();

      for (bfu var11 : var1.getMethods()) {
         if (var11.isRenamed()) {
            this.ys.put(var11.getSignature(false), var11.getName(true));
         }
      }

      this.ld = new HashMap(var1.ah());
      if (var1.Sc != null && !var1.Sc.pC()) {
         this.gp = var1.Sc;
      }

      this.fI = new LinkedHashMap();

      for (BookmarkManager.Bookmark var12 : var1.getParentProject().getBookmarkManager().get(var1)) {
         this.fI.put(var12.getAddress(), var12.getDescription());
      }
   }

   @Override
   public boolean isTargetUnit(IUnit var1) {
      if (!(var1 instanceof vi)) {
         return false;
      } else {
         try {
            int var2 = Hash.calculateCRC32(((vi)var1).E());
            return var2 == this.A;
         } catch (IOException var3) {
            return false;
         }
      }
   }

   @Override
   public boolean reload(IUnit var1) {
      vi var2 = (vi)var1;
      int var3 = 0;
      if (this.gp == null && this.UT != null) {
         for (Entry var5 : this.UT.entrySet()) {
            qt var6 = var2.ld().A((String)var5.getKey(), false);
            if (var6 != null) {
               var6.pC((String)var5.getValue(), true, false);
               var3++;
            }
         }
      }

      if (this.E != null) {
         for (Entry var18 : this.E.entrySet()) {
            bfs var27 = var2.gp((String)var18.getKey());
            if (var27 != null) {
               var27.setName((String)var18.getValue(), false);
               var3++;
            }
         }
      }

      if (this.sY != null) {
         for (Entry var19 : this.sY.entrySet()) {
            bft var28 = var2.kS((String)var19.getKey());
            if (var28 != null) {
               var28.setName((String)var19.getValue(), false);
               var3++;
            }
         }
      }

      if (this.ys != null) {
         for (Entry var20 : this.ys.entrySet()) {
            bfu var29 = var2.wS((String)var20.getKey());
            if (var29 != null) {
               var29.setName((String)var20.getValue(), false);
               var3++;
            }
         }
      }

      if (this.gp != null) {
         bgj var14 = var2.ld();

         for (vi.Av.Av var30 : this.gp.A()) {
            boolean var7 = false;
            if (var30.pC == 1) {
               var7 = var14.pC(var30.A, var30.kS, false);
            } else if (var30.pC == 2) {
               var7 = var14.pC(var30.A, true, false) != null;
            } else if (var30.pC == 3) {
               var7 = var14.pC(var30.A, var30.kS, false, false);
            } else if (var30.pC == 4) {
               var7 = var14.A(var30.A, var30.kS, false, false);
            } else if (var30.pC == 5) {
               var7 = var14.pC(var30.A, var30.kS, var30.wS, false, false);
            }

            if (!var7) {
               Object[] var10000 = new Object[]{var30};
            }
         }
      }

      if (this.wS != null) {
         for (Entry var22 : this.wS.entrySet()) {
            if (var2.A((String)var22.getKey(), (String)var22.getValue(), false)) {
               var3++;
            }
         }
      }

      if (this.kS != null) {
         for (Entry var23 : this.kS.entrySet()) {
            if (var2.pC((String)var23.getKey(), 0, (String)var23.getValue(), false)) {
               var3++;
            }
         }
      }

      DexCommentManager var17 = var2.getCommentManager();
      if (var17 != null && this.oT != null) {
         var17.putAll(this.oT);
         var3 += this.oT.size();
         if (this.fI != null) {
            BookmarkManager var24 = var2.getParentProject().getBookmarkManager();

            for (Entry var34 : this.fI.entrySet()) {
               var24.set(var2, (String)var34.getKey(), (String)var34.getValue(), true);
            }
         } else {
            BookmarkManager var25 = var2.getParentProject().getBookmarkManager();

            for (ICodeCoordinates var35 : this.oT.keySet()) {
               Comment var8 = (Comment)this.oT.get(var35);
               Optional var9 = var8.getMetaComments(4096, 0).stream().findFirst();
               if (var9.isPresent()) {
                  String var10 = var2.A().pC(var35);
                  if (var10 != null) {
                     var25.set(var2, var10, ((MetaComment)var9.get()).getValue(), true);
                  }
               }
            }
         }
      }

      if (this.ld != null) {
         for (Entry var33 : this.ld.entrySet()) {
            var2.pC((IdentifierCoordinates)var33.getKey(), (String)var33.getValue(), false, false);
         }
      }

      var2.pC(var3 > 0);
      return true;
   }
}
