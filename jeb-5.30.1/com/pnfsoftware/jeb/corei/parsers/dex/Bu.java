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
import com.pnfsoftware.jebglobal.bjn;
import com.pnfsoftware.jebglobal.bjo;
import com.pnfsoftware.jebglobal.bjp;
import com.pnfsoftware.jebglobal.bke;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Map.Entry;

@Ser
@SerVersion(1)
public class Bu implements IQuickStateObject {
   private static final ILogger q = GlobalLog.getLogger(Bu.class);
   @SerId(1)
   private int RF;
   @SerId(2)
   private Map xK;
   @SerId(3)
   private Map Dw;
   @SerId(4)
   private Map Uv;
   @SerId(5)
   private Map oW;
   @SerId(6)
   private Map gO;
   @SerId(7)
   private Map nf;
   @SerId(value = 8, version = 1)
   private Map gP;
   @SerId(value = 9, version = 1)
   private bK.CU za;
   @SerId(10)
   private Map lm;
   @SerId(11)
   private Map zz;

   public Bu(bK var1) throws IOException {
      this.RF = Hash.calculateCRC32(var1.zz());
      DexCommentManager var2 = var1.getCommentManager();
      this.lm = new HashMap(var2.getComments2());
      this.Dw = new HashMap(var1.getAddressLabels());
      this.Uv = new LinkedHashMap();

      for (Vj var4 : var1.getPackages()) {
         if (var4.isRenamed()) {
            this.Uv.put(var4.getSignature(false), var4.getName(true));
         }
      }

      this.oW = new LinkedHashMap();

      for (bjn var9 : var1.getClasses()) {
         if (var9.isRenamed()) {
            this.oW.put(var9.getSignature(false), var9.getName(true));
         }
      }

      this.gO = new LinkedHashMap();

      for (bjo var10 : var1.getFields()) {
         if (var10.isRenamed()) {
            this.gO.put(var10.getSignature(false), var10.getName(true));
         }
      }

      this.nf = new LinkedHashMap();

      for (bjp var11 : var1.getMethods()) {
         if (var11.isRenamed()) {
            this.nf.put(var11.getSignature(false), var11.getName(true));
         }
      }

      this.gP = new HashMap(var1.sH());
      if (var1.Hk != null && !var1.Hk.q()) {
         this.za = var1.Hk;
      }

      this.zz = new LinkedHashMap();

      for (BookmarkManager.Bookmark var12 : var1.getParentProject().getBookmarkManager().get(var1)) {
         this.zz.put(var12.getAddress(), var12.getDescription());
      }
   }

   @Override
   public boolean isTargetUnit(IUnit var1) {
      if (!(var1 instanceof bK)) {
         return false;
      } else {
         try {
            int var2 = Hash.calculateCRC32(((bK)var1).zz());
            return var2 == this.RF;
         } catch (IOException var3) {
            return false;
         }
      }
   }

   @Override
   public boolean reload(IUnit var1) {
      bK var2 = (bK)var1;
      int var3 = 0;
      if (this.za == null && this.Uv != null) {
         for (Entry var5 : this.Uv.entrySet()) {
            Vj var6 = var2.io().RF((String)var5.getKey(), false);
            if (var6 != null) {
               var6.q((String)var5.getValue(), true, false);
               var3++;
            }
         }
      }

      if (this.oW != null) {
         for (Entry var18 : this.oW.entrySet()) {
            bjn var27 = var2.za((String)var18.getKey());
            if (var27 != null) {
               var27.q((String)var18.getValue(), false);
               var3++;
            }
         }
      }

      if (this.gO != null) {
         for (Entry var19 : this.gO.entrySet()) {
            bjo var28 = var2.xK((String)var19.getKey());
            if (var28 != null) {
               var28.q((String)var19.getValue(), false);
               var3++;
            }
         }
      }

      if (this.nf != null) {
         for (Entry var20 : this.nf.entrySet()) {
            bjp var29 = var2.Dw((String)var20.getKey());
            if (var29 != null) {
               var29.q((String)var20.getValue(), false);
               var3++;
            }
         }
      }

      if (this.za != null) {
         bke var14 = var2.io();

         for (bK.CU.eo var30 : this.za.RF()) {
            boolean var7 = false;
            if (var30.q == 1) {
               var7 = var14.q(var30.RF, var30.xK, false);
            } else if (var30.q == 2) {
               var7 = var14.q(var30.RF, true, false) != null;
            } else if (var30.q == 3) {
               var7 = var14.q(var30.RF, var30.xK, false, false);
            } else if (var30.q == 4) {
               var7 = var14.RF(var30.RF, var30.xK, false, false);
            } else if (var30.q == 5) {
               var7 = var14.q(var30.RF, var30.xK, var30.Dw, false, false);
            }

            if (!var7) {
               Object[] var10000 = new Object[]{var30};
            }
         }
      }

      if (this.Dw != null) {
         for (Entry var22 : this.Dw.entrySet()) {
            if (var2.RF((String)var22.getKey(), (String)var22.getValue(), false)) {
               var3++;
            }
         }
      }

      if (this.xK != null) {
         for (Entry var23 : this.xK.entrySet()) {
            if (var2.q((String)var23.getKey(), 0, (String)var23.getValue(), false)) {
               var3++;
            }
         }
      }

      DexCommentManager var17 = var2.getCommentManager();
      if (var17 != null && this.lm != null) {
         var17.putAll(this.lm);
         var3 += this.lm.size();
         if (this.zz != null) {
            BookmarkManager var24 = var2.getParentProject().getBookmarkManager();

            for (Entry var34 : this.zz.entrySet()) {
               var24.set(var2, (String)var34.getKey(), (String)var34.getValue(), true);
            }
         } else {
            BookmarkManager var25 = var2.getParentProject().getBookmarkManager();

            for (ICodeCoordinates var35 : this.lm.keySet()) {
               Comment var8 = (Comment)this.lm.get(var35);
               Optional var9 = var8.getMetaComments(4096, 0).stream().findFirst();
               if (var9.isPresent()) {
                  String var10 = var2.xK().q(var35);
                  if (var10 != null) {
                     var25.set(var2, var10, ((MetaComment)var9.get()).getValue(), true);
                  }
               }
            }
         }
      }

      if (this.gP != null) {
         for (Entry var33 : this.gP.entrySet()) {
            var2.q((IdentifierCoordinates)var33.getKey(), (String)var33.getValue(), false, false);
         }
      }

      var2.oW(var3 > 0);
      return true;
   }
}
