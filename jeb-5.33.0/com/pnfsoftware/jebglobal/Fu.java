package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.BookmarkManager;
import com.pnfsoftware.jeb.core.input.IInput;
import com.pnfsoftware.jeb.core.units.IQuickStateObject;
import com.pnfsoftware.jeb.core.units.IUnit;
import com.pnfsoftware.jeb.core.units.code.NativeCommentManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ICommentManager;
import com.pnfsoftware.jeb.util.encoding.Hash;
import com.pnfsoftware.jeb.util.io.ByteBufferUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

@Ser
public class Fu implements IQuickStateObject {
   @SerId(1)
   private int pC;
   @SerId(2)
   private int A;
   @SerId(3)
   private Map kS;
   @SerId(4)
   private Map wS;
   @SerId(5)
   private Map UT;
   @SerId(6)
   private Map E;

   public Fu(C var1) {
      this.pC = (int)var1.getInput().getCurrentSize();
      byte[] var2 = ByteBufferUtil.getBytes(var1.getInput().getHeader());
      this.A = Hash.calculateCRC32(var2);
      this.kS = new HashMap(var1.getCodeModel().getLabelManager().getPrimaryLabels());
      this.UT = new HashMap(var1.getCommentManager().getComments2());
      this.E = new LinkedHashMap();

      for (BookmarkManager.Bookmark var4 : var1.getParentProject().getBookmarkManager().get(var1)) {
         this.E.put(var4.getAddress(), var4.getDescription());
      }
   }

   @Override
   public boolean isTargetUnit(IUnit var1) {
      if (!(var1 instanceof C)) {
         return false;
      } else {
         IInput var2 = ((C)var1).getInput();
         if (var2.getCurrentSize() != this.pC) {
            return false;
         } else {
            byte[] var3 = ByteBufferUtil.getBytes(var2.getHeader());
            int var4 = Hash.calculateCRC32(var3);
            return var4 == this.A;
         }
      }
   }

   @Override
   public boolean reload(IUnit var1) {
      C var2 = (C)var1;
      if (var2.getCodeModel() == null) {
         return false;
      } else {
         if (this.kS != null) {
            for (Entry var4 : this.kS.entrySet()) {
               var2.pC((Long)var4.getKey(), (String)var4.getValue());
            }
         }

         NativeCommentManager var10 = var2.getCommentManager();
         if (this.wS != null) {
            ICommentManager var11 = var2.getCodeModel().getCommentManager();

            for (Entry var6 : this.wS.entrySet()) {
               long var7 = (Long)var6.getKey();
               String var9 = (String)var6.getValue();
               if (var7 != -1L && var9 != null) {
                  var11.setComment(var7, var9);
               }
            }
         }

         if (var10 != null && this.UT != null) {
            var10.putAll(this.UT);
            if (this.E != null) {
               BookmarkManager var12 = var2.getParentProject().getBookmarkManager();

               for (Entry var14 : this.E.entrySet()) {
                  var12.set(var2, (String)var14.getKey(), (String)var14.getValue(), true);
               }
            }
         }

         return true;
      }
   }
}
