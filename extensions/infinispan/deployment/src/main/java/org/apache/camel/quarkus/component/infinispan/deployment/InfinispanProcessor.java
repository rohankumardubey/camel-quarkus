/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.quarkus.component.infinispan.deployment;

import io.quarkus.deployment.annotations.BuildStep;
import io.quarkus.deployment.builditem.FeatureBuildItem;
import io.quarkus.deployment.builditem.nativeimage.NativeImageResourceBuildItem;
import io.quarkus.deployment.builditem.nativeimage.ReflectiveClassBuildItem;
import org.infinispan.commons.marshall.ProtoStreamMarshaller;

class InfinispanProcessor {

    private static final String FEATURE = "camel-infinispan";

    @BuildStep
    FeatureBuildItem feature() {
        return new FeatureBuildItem(FEATURE);
    }

    @BuildStep
    NativeImageResourceBuildItem nativeImageResources() {
        // Only required when Camel instantiates and manages its own internal CacheContainer
        return new NativeImageResourceBuildItem("org/infinispan/protostream/message-wrapping.proto");
    }

    @BuildStep
    ReflectiveClassBuildItem reflectiveClasses() {
        // Only required when Camel instantiates and manages its own internal CacheContainer
        return new ReflectiveClassBuildItem(false, false, ProtoStreamMarshaller.class);
    }
}
