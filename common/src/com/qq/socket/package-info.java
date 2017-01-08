/**
 * socket包是我自己实现的一个简单的TCP框架（基于Socket和ServerSocket）。
 * 它可以自动处理TCP中的线程模型，并且解决了一些可能出现的（甚至是罕见的）BUG。
 * 工作流程：
 * 当一个服务端（Server）启动后，它将启动一个ServerListener线程（boss worker）用于监听客户端的连接；
 * 每当一个客户（Client)连接后，将启动一个ServerClient线程与之保持连接并可互通消息。
 * 当产生消息时，触发SocketListener的onMessage事件，可以得到消息的内容。
 *
 * 设计模式主要是监听模式
 */
package com.qq.socket;