package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaFor extends IJavaCompound {
   IJavaStatement getInitializer();

   IJavaPredicate getPredicate();

   IJavaStatement getPostStatement();

   IJavaBlock getBody();

   void setInitializer(IJavaStatement var1);

   void setPredicate(IJavaPredicate var1);

   void setPostStatement(IJavaStatement var1);

   void setBody(IJavaBlock var1);

   IJavaFor duplicate();
}
