package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.core.events.J;
import com.pnfsoftware.jeb.core.events.JebEvent;
import com.pnfsoftware.jeb.core.units.IInteractiveUnit;
import com.pnfsoftware.jeb.core.units.UnitChangeEventData;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Ser
public abstract class AbstractCommentManager {
   public static final int INLINE = 0;
   public static final int PRE = -1;
   public static final int COMMENT_FLAG_FAVORITE = 4096;
   private static Comment EMPTY = new Comment();
   @SerId(1)
   private IInteractiveUnit unit;
   @SerId(2)
   private Map comments = new ConcurrentHashMap();

   public AbstractCommentManager(IInteractiveUnit var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.unit = var1;
      }
   }

   public IInteractiveUnit getUnit() {
      return this.unit;
   }

   public Map getComments() {
      HashMap var1 = new HashMap();

      for (Entry var3 : this.comments.entrySet()) {
         Object var4 = var3.getKey();
         Comment var5 = (Comment)var3.getValue();
         if (var4 != null && var5 != null) {
            String var6 = this.coordToAddress(var4);
            if (var6 != null) {
               var1.put(var6, var5);
            }
         }
      }

      return var1;
   }

   public Map getComments2() {
      return this.comments;
   }

   Comment getSafe(String var1) {
      Object var2 = this.addressToCoord(var1);
      if (var2 != null) {
         Comment var3 = (Comment)this.comments.get(var2);
         if (var3 != null) {
            return var3;
         }
      }

      return EMPTY;
   }

   Comment getSafe2(Object var1) {
      if (var1 != null) {
         Comment var2 = (Comment)this.comments.get(var1);
         if (var2 != null) {
            return var2;
         }
      }

      return EMPTY;
   }

   public Comment getComment(String var1) {
      Object var2 = this.addressToCoord(var1);
      return var2 == null ? null : (Comment)this.comments.get(var2);
   }

   public Comment getComment2(Object var1) {
      return var1 == null ? null : (Comment)this.comments.get(var1);
   }

   public boolean setComment(String var1, Comment var2, boolean var3) {
      Object var4 = this.addressToCoord(var1);
      return var4 == null ? false : this.setComment2(var4, var2, var3);
   }

   public boolean setComment2(Object var1, Comment var2, boolean var3) {
      if (var1 == null) {
         return false;
      } else {
         Comment var4;
         synchronized (this.comments) {
            var4 = (Comment)this.comments.put(var1, var2);
            if (var4 == var2) {
               return true;
            }
         }

         this.notifyChangeIf(var3, new UnitChangeEventData(3, this.unit, var2, var4, var1));
         return true;
      }
   }

   public String getInlineComment(String var1) {
      return this.getSafe(var1).getInline();
   }

   public boolean setInlineComment(String var1, String var2) {
      return this.setPrimary(var1, var2, 0, true);
   }

   public boolean setInlineComment(String var1, String var2, boolean var3) {
      return this.setPrimary(var1, var2, 0, var3);
   }

   public String getInlineComment2(Object var1) {
      return this.getSafe2(var1).getInline();
   }

   public boolean setInlineComment2(Object var1, String var2) {
      return this.setPrimary2(var1, var2, 0, true);
   }

   public boolean setInlineComment2(Object var1, String var2, boolean var3) {
      return this.setPrimary2(var1, var2, 0, var3);
   }

   public String getPreComment(String var1) {
      return this.getSafe(var1).getPre();
   }

   public boolean setPreComment(String var1, String var2) {
      return this.setPrimary(var1, var2, -1, true);
   }

   public boolean setPreComment(String var1, String var2, boolean var3) {
      return this.setPrimary(var1, var2, -1, var3);
   }

   public String getPreComment2(Object var1) {
      return this.getSafe2(var1).getPre();
   }

   public boolean setPreComment2(Object var1, String var2) {
      return this.setPrimary2(var1, var2, -1, true);
   }

   public boolean setPreComment2(Object var1, String var2, boolean var3) {
      return this.setPrimary2(var1, var2, -1, var3);
   }

   public String getPrimary(String var1, int var2) {
      Object var3 = this.addressToCoord(var1);
      return var3 == null ? null : this.getPrimary2(var3, var2);
   }

   public String getPrimary2(Object var1, int var2) {
      return this.getSafe2(var1).getPrimary(var2);
   }

   public boolean setPrimary(String var1, String var2, int var3, boolean var4) {
      Object var5 = this.addressToCoord(var1);
      return var5 == null ? false : this.setPrimary2(var5, var2, var3, var4);
   }

   public boolean setPrimary2(Object var1, String var2, int var3, boolean var4) {
      if (var1 == null) {
         return false;
      } else {
         String var5;
         synchronized (this.comments) {
            Comment var7 = (Comment)this.comments.get(var1);
            if (var7 == null) {
               if (var2 == null || var2.isEmpty()) {
                  return true;
               }

               var7 = new Comment();
               this.comments.put(var1, var7);
               var5 = var7.setPrimary(var3, var2);
            } else {
               var5 = var7.getPrimary(var3);
               if (var5 == null && var2 == null || var5 != null && var5.equals(var2)) {
                  return true;
               }

               if (var2 != null && var2.length() == 0) {
                  var2 = null;
               }

               var7.setPrimary(var3, var2);
               if (var7.isEmpty()) {
                  this.comments.remove(var1);
               }
            }
         }

         this.notifyChangeIf(var4, new UnitChangeEventData(3, this.unit, var2, var5, var1));
         return true;
      }
   }

   public Map getPrimaryMap(int var1) {
      HashMap var2 = new HashMap();

      for (Entry var4 : this.comments.entrySet()) {
         Object var5 = var4.getKey();
         Comment var6 = (Comment)var4.getValue();
         if (var5 != null && var6 != null && var6.getPrimary(var1) != null) {
            String var7 = this.coordToAddress(var5);
            if (var7 != null) {
               var2.put(var7, var6.getPrimary(var1));
            }
         }
      }

      return var2;
   }

   public Map getPrimaryMap2(int var1) {
      HashMap var2 = new HashMap();

      for (Entry var4 : this.comments.entrySet()) {
         Object var5 = var4.getKey();
         Comment var6 = (Comment)var4.getValue();
         if (var5 != null && var6 != null && var6.getPrimary(var1) != null) {
            var2.put(var5, var6.getPrimary(var1));
         }
      }

      return var2;
   }

   public Collection getMetaComments(String var1) {
      Object var2 = this.addressToCoord(var1);
      return (Collection)(var2 == null ? Collections.emptyList() : this.getMetaComments2(var2));
   }

   public Collection getMetaComments2(Object var1) {
      return this.getSafe2(var1).getMetaComments();
   }

   public Collection getMetaComments(String var1, int var2, int var3) {
      return (Collection)this.getMetaComments(var1)
         .stream()
         .filter(var2x -> (var2x.getFlags() & var2) == var2 && (var2x.getFlags() & var3) == 0)
         .collect(Collectors.toList());
   }

   public Collection getMetaComments2(Object var1, int var2, int var3) {
      return (Collection)this.getMetaComments2(var1)
         .stream()
         .filter(var2x -> (var2x.getFlags() & var2) == var2 && (var2x.getFlags() & var3) == 0)
         .collect(Collectors.toList());
   }

   public boolean addMetaComment(String var1, MetaComment var2, boolean var3) {
      Object var4 = this.addressToCoord(var1);
      return var4 == null ? false : this.addMetaComment2(var4, var2, var3);
   }

   public boolean addMetaComment2(Object var1, MetaComment var2, boolean var3) {
      if (var1 == null) {
         return false;
      } else {
         Comment var4 = this.create(var1);
         if (!var4.addMetaComment(var2)) {
            return false;
         } else {
            this.notifyChangeIf(var3, new UnitChangeEventData(3, this.unit, var2.getValue(), null, var1));
            return true;
         }
      }
   }

   public boolean removeMetaComment(String var1, MetaComment var2, boolean var3) {
      Object var4 = this.addressToCoord(var1);
      return var4 == null ? false : this.removeMetaComment2(var4, var2, var3);
   }

   public boolean removeMetaComment2(Object var1, MetaComment var2, boolean var3) {
      if (var1 == null) {
         return false;
      } else {
         Comment var4 = this.getComment2(var1);
         if (var4 != null && var4.removeMetaComment(var2)) {
            if (var4.isEmpty()) {
               this.comments.remove(var1);
            }

            this.notifyChangeIf(var3, new UnitChangeEventData(3, this.unit, var2.getValue(), null, var1));
            return true;
         } else {
            return false;
         }
      }
   }

   public Collection getMetaCommentObjects(String var1, int var2, int var3) {
      return (Collection)this.getMetaComments(var1)
         .stream()
         .filter(var2x -> (var2x.getFlags() & var2) == var2 && (var2x.getFlags() & var3) == 0)
         .map(var0 -> var0.getValue())
         .collect(Collectors.toList());
   }

   public Collection getMetaCommentObjects2(Object var1, int var2, int var3) {
      return (Collection)this.getMetaComments2(var1)
         .stream()
         .filter(var2x -> (var2x.getFlags() & var2) == var2 && (var2x.getFlags() & var3) == 0)
         .map(var0 -> var0.getValue())
         .collect(Collectors.toList());
   }

   public String formatComments(String var1, boolean var2, int var3, int var4) {
      Object var5 = this.addressToCoord(var1);
      return var5 == null ? "" : this.formatComments2(var5, var2, var3, var4);
   }

   public String formatComments2(Object var1, boolean var2, int var3, int var4) {
      if (var1 == null) {
         return "";
      } else {
         String var5 = Strings.safe(this.getPrimary2(var1, 0));
         Collection var6 = this.getMetaComments2(var1);
         if (var6.isEmpty()) {
            return var5;
         } else {
            StringBuilder var7 = new StringBuilder(var5);

            for (MetaComment var9 : var6) {
               if ((var9.getFlags() & var3) == var3 && (var9.getFlags() & var4) == 0) {
                  if (var7.length() > 0) {
                     var7.append('\n');
                  }

                  var7.append(var9.getValue());
               }
            }

            return var7.toString();
         }
      }
   }

   public String formatMetaComment(MetaComment var1) {
      return var1.getValue().toString() + " (" + this.formatMetaFlags(var1.getFlags()) + ")";
   }

   public String formatMetaFlags(int var1) {
      return Strings.ff("0x%X", var1);
   }

   public abstract Object addressToCoord(String var1);

   public abstract String coordToAddress(Object var1);

   private Comment create(Object var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         Comment var2 = (Comment)this.comments.get(var1);
         if (var2 == null) {
            var2 = new Comment();
            this.comments.put(var1, var2);
         }

         return var2;
      }
   }

   private void notifyChangeIf(boolean var1, UnitChangeEventData var2) {
      if (var1) {
         this.unit.notifyListeners(new JebEvent(J.UnitChange, var2));
      }
   }

   public void putAll(Map var1) {
      this.comments.putAll(var1);
   }
}
