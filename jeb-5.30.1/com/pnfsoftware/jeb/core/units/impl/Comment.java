package com.pnfsoftware.jeb.core.units.impl;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.stream.Collectors;

@Ser
public class Comment {
   @SerId(1)
   private String inline;
   @SerId(2)
   private String pre;
   @SerId(3)
   private Collection metacoll;

   public Comment() {
   }

   public Comment(String var1) {
      this.inline = var1;
   }

   public Comment(String var1, String var2) {
      this.inline = var1;
      this.pre = var2;
   }

   public Comment(String var1, String var2, Collection var3) {
      this.inline = var1;
      this.pre = var2;
      this.metacoll = new LinkedHashSet(var3);
   }

   public void setInline(String var1) {
      this.inline = var1;
   }

   public String getInline() {
      return this.inline;
   }

   public void setPre(String var1) {
      this.pre = var1;
   }

   public String getPre() {
      return this.pre;
   }

   String setPrimary(int var1, String var2) {
      String var3;
      if (var1 >= 0) {
         var3 = this.inline;
         this.inline = var2;
      } else {
         var3 = this.pre;
         this.pre = var2;
      }

      return var3;
   }

   String getPrimary(int var1) {
      return var1 >= 0 ? this.inline : this.pre;
   }

   public Collection getMetaComments() {
      return (Collection)(this.metacoll == null ? Collections.emptyList() : Collections.unmodifiableCollection(this.metacoll));
   }

   public Collection getMetaComments(int var1, int var2) {
      return (Collection)(this.metacoll == null
         ? Collections.emptyList()
         : (Collection)this.metacoll.stream().filter(var2x -> (var2x.getFlags() & var1) == var1 && (var2x.getFlags() & var2) == 0).collect(Collectors.toList()));
   }

   boolean addMetaComment(MetaComment var1) {
      if (this.metacoll == null) {
         this.metacoll = new LinkedHashSet();
      }

      return this.metacoll.add(var1);
   }

   boolean removeMetaComment(MetaComment var1) {
      if (this.metacoll == null) {
         return false;
      } else {
         boolean var2 = this.metacoll.remove(var1);
         if (this.metacoll.isEmpty()) {
            this.metacoll = null;
         }

         return var2;
      }
   }

   public boolean isEmpty() {
      return (this.inline == null || this.inline.isEmpty()) && (this.pre == null || this.pre.isEmpty()) && (this.metacoll == null || this.metacoll.isEmpty());
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.inline == null ? 0 : this.inline.hashCode());
      var1 = 31 * var1 + (this.metacoll == null ? 0 : this.metacoll.hashCode());
      return 31 * var1 + (this.pre == null ? 0 : this.pre.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         Comment var2 = (Comment)var1;
         if (this.inline == null) {
            if (var2.inline != null) {
               return false;
            }
         } else if (!this.inline.equals(var2.inline)) {
            return false;
         }

         if (this.metacoll == null) {
            if (var2.metacoll != null) {
               return false;
            }
         } else if (!this.metacoll.equals(var2.metacoll)) {
            return false;
         }

         if (this.pre == null) {
            if (var2.pre != null) {
               return false;
            }
         } else if (!this.pre.equals(var2.pre)) {
            return false;
         }

         return true;
      }
   }

   public String formatRaw() {
      StringBuilder var1 = new StringBuilder();
      if (this.inline != null) {
         var1.append(this.inline);
      }

      if (this.pre != null) {
         if (var1.length() > 0) {
            var1.append("\n");
         }

         var1.append(this.pre);
      }

      if (this.metacoll != null) {
         for (MetaComment var3 : this.metacoll) {
            if (var1.length() > 0) {
               var1.append("\n");
            }

            var1.append(var3.getValue().toString());
         }
      }

      return var1.toString();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.inline == null) {
         Strings.ff(var1, "()");
      } else {
         Strings.ff(var1, "\"%s\"", this.inline);
      }

      if (this.pre != null) {
         Strings.ff(var1, ";pre:\"%s\"", this.pre);
      }

      if (this.metacoll != null) {
         Strings.ff(var1, ";meta:{%s}", this.metacoll);
      }

      return var1.toString();
   }
}
