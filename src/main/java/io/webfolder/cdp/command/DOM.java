/**
 * cdp4j - Chrome DevTools Protocol for Java
 * Copyright © 2017 WebFolder OÜ (support@webfolder.io)
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * <p>
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package io.webfolder.cdp.command;

import io.webfolder.cdp.annotation.Domain;
import io.webfolder.cdp.annotation.Experimental;
import io.webfolder.cdp.annotation.Optional;
import io.webfolder.cdp.annotation.Returns;
import io.webfolder.cdp.type.dom.BoxModel;
import io.webfolder.cdp.type.dom.Node;
import io.webfolder.cdp.type.dom.PerformSearchResult;
import io.webfolder.cdp.type.runtime.RemoteObject;

import java.util.List;

/**
 * This domain exposes DOM read/write operations
 * Each DOM Node is represented with its mirror object that has an <code>id</code>
 * This <code>id</code> can be used to get additional information on the Node, resolve it into the JavaScript object wrapper, etc
 * It is important that client receives DOM events only for the nodes that are known to the client
 * Backend keeps track of the nodes that were sent to the client and never sends the same node twice
 * It is client's responsibility to collect information about the nodes that were sent to the client
 * <p>Note that <code>iframe</code> owner elements will return corresponding document elements as their child nodes
 * </p>
 */
@Domain("DOM")
public interface DOM {
    /**
     * Enables DOM agent for the given page.
     */
    void enable();

    /**
     * Disables DOM agent for the given page.
     */
    void disable();

    /**
     * Returns the root DOM node (and optionally the subtree) to the caller.
     *
     * @param depth  The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
     * @param pierce Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
     * @return Resulting node.
     */
    @Returns("root")
    Node getDocument(@Experimental @Optional Integer depth, @Experimental @Optional Boolean pierce);

    /**
     * Returns the root DOM node (and optionally the subtree) to the caller.
     *
     * @param depth  The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
     * @param pierce Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
     * @return Resulting node.
     */
    @Returns("nodes")
    List<Node> getFlattenedDocument(@Experimental @Optional Integer depth,
                                    @Experimental @Optional Boolean pierce);

    /**
     * Collects class names for the node with given id and all of it's child nodes.
     *
     * @param nodeId Id of the node to collect class names.
     * @return Class name list.
     */
    @Experimental
    @Returns("classNames")
    List<String> collectClassNamesFromSubtree(Integer nodeId);

    /**
     * Requests that children of the node with given id are returned to the caller in form of <tt>setChildNodes</tt> events where not only immediate children are retrieved, but all children down to the specified depth.
     *
     * @param nodeId Id of the node to get children for.
     * @param depth  The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
     * @param pierce Whether or not iframes and shadow roots should be traversed when returning the sub-tree (default is false).
     */
    void requestChildNodes(Integer nodeId, @Experimental @Optional Integer depth,
                           @Experimental @Optional Boolean pierce);

    /**
     * Executes <tt>querySelector</tt> on a given node.
     *
     * @param nodeId   Id of the node to query upon.
     * @param selector Selector string.
     * @return Query selector result.
     */
    @Returns("nodeId")
    Integer querySelector(Integer nodeId, String selector);

    /**
     * Executes <tt>querySelectorAll</tt> on a given node.
     *
     * @param nodeId   Id of the node to query upon.
     * @param selector Selector string.
     * @return Query selector result.
     */
    @Returns("nodeIds")
    List<Integer> querySelectorAll(Integer nodeId, String selector);

    /**
     * Sets node name for a node with given id.
     *
     * @param nodeId Id of the node to set name for.
     * @param name   New node's name.
     * @return New node's id.
     */
    @Returns("nodeId")
    Integer setNodeName(Integer nodeId, String name);

    /**
     * Sets node value for a node with given id.
     *
     * @param nodeId Id of the node to set value for.
     * @param value  New node's value.
     */
    void setNodeValue(Integer nodeId, String value);

    /**
     * Removes node with given id.
     *
     * @param nodeId Id of the node to remove.
     */
    void removeNode(Integer nodeId);

    /**
     * Sets attribute for an element with given id.
     *
     * @param nodeId Id of the element to set attribute for.
     * @param name   Attribute name.
     * @param value  Attribute value.
     */
    void setAttributeValue(Integer nodeId, String name, String value);

    /**
     * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
     *
     * @param nodeId Id of the element to set attributes for.
     * @param text   Text with a number of attributes. Will parse this text using HTML parser.
     * @param name   Attribute name to replace with new attributes derived from text in case text parsed successfully.
     */
    void setAttributesAsText(Integer nodeId, String text, @Optional String name);

    /**
     * Removes attribute with given name from an element with given id.
     *
     * @param nodeId Id of the element to remove attribute from.
     * @param name   Name of the attribute to remove.
     */
    void removeAttribute(Integer nodeId, String name);

    /**
     * Returns node's HTML markup.
     *
     * @param nodeId        Identifier of the node.
     * @param backendNodeId Identifier of the backend node.
     * @param objectId      JavaScript object id of the node wrapper.
     * @return Outer HTML markup.
     */
    @Returns("outerHTML")
    String getOuterHTML(@Optional Integer nodeId, @Optional Integer backendNodeId,
                        @Optional String objectId);

    /**
     * Returns node's HTML markup.
     *
     * @param nodeId Identifier of the node.
     * @return Outer HTML markup.
     */
    @Returns("outerHTML")
    String getOuterHTML(Integer nodeId);

    /**
     * Sets node HTML markup, returns new node id.
     *
     * @param nodeId    Id of the node to set markup for.
     * @param outerHTML Outer HTML markup to set.
     */
    void setOuterHTML(Integer nodeId, String outerHTML);

    /**
     * Searches for a given string in the DOM tree. Use <tt>getSearchResults</tt> to access search results or <tt>cancelSearch</tt> to end this search session.
     *
     * @param query                     Plain text or query selector or XPath search query.
     * @param includeUserAgentShadowDOM True to search in user agent shadow DOM.
     * @return PerformSearchResult
     */
    @Experimental
    PerformSearchResult performSearch(String query,
                                      @Experimental @Optional Boolean includeUserAgentShadowDOM);

    /**
     * Returns search results from given <tt>fromIndex</tt> to given <tt>toIndex</tt> from the search with the given identifier.
     *
     * @param searchId  Unique search session identifier.
     * @param fromIndex Start index of the search result to be returned.
     * @param toIndex   End index of the search result to be returned.
     * @return Ids of the search result nodes.
     */
    @Experimental
    @Returns("nodeIds")
    List<Integer> getSearchResults(String searchId, Integer fromIndex, Integer toIndex);

    /**
     * Discards search results from the session with the given id. <tt>getSearchResults</tt> should no longer be called for that search.
     *
     * @param searchId Unique search session identifier.
     */
    @Experimental
    void discardSearchResults(String searchId);

    /**
     * Requests that the node is sent to the caller given the JavaScript node object reference. All nodes that form the path from the node to the root are also sent to the client as a series of <tt>setChildNodes</tt> notifications.
     *
     * @param objectId JavaScript object id to convert into node.
     * @return Node id for given object.
     */
    @Returns("nodeId")
    Integer requestNode(String objectId);

    /**
     * Highlights given rectangle.
     */
    void highlightRect();

    /**
     * Highlights DOM node.
     */
    void highlightNode();

    /**
     * Hides any highlight.
     */
    void hideHighlight();

    /**
     * Requests that the node is sent to the caller given its path. // FIXME, use XPath
     *
     * @param path Path to node in the proprietary format.
     * @return Id of the node for given path.
     */
    @Experimental
    @Returns("nodeId")
    Integer pushNodeByPathToFrontend(String path);

    /**
     * Requests that a batch of nodes is sent to the caller given their backend node ids.
     *
     * @param backendNodeIds The array of backend node ids.
     * @return The array of ids of pushed nodes that correspond to the backend ids specified in backendNodeIds.
     */
    @Experimental
    @Returns("nodeIds")
    List<Integer> pushNodesByBackendIdsToFrontend(List<Integer> backendNodeIds);

    /**
     * Enables console to refer to the node with given id via  (see Command Line API for more details  functions).
     *
     * @param nodeId DOM node id to be accessible by means of x command line API.
     */
    @Experimental
    void setInspectedNode(Integer nodeId);

    /**
     * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
     *
     * @param nodeId        Id of the node to resolve.
     * @param backendNodeId Backend identifier of the node to resolve.
     * @param objectGroup   Symbolic group name that can be used to release multiple objects.
     * @return JavaScript object wrapper for given node.
     */
    @Returns("object")
    RemoteObject resolveNode(@Optional Integer nodeId, @Optional Integer backendNodeId,
                             @Optional String objectGroup);

    /**
     * Returns attributes for the specified node.
     *
     * @param nodeId Id of the node to retrieve attibutes for.
     * @return An interleaved array of node attribute names and values.
     */
    @Returns("attributes")
    List<String> getAttributes(Integer nodeId);

    /**
     * Creates a deep copy of the specified node and places it into the target container before the given anchor.
     *
     * @param nodeId             Id of the node to copy.
     * @param targetNodeId       Id of the element to drop the copy into.
     * @param insertBeforeNodeId Drop the copy before this node (if absent, the copy becomes the last child of <code>targetNodeId</code>).
     * @return Id of the node clone.
     */
    @Experimental
    @Returns("nodeId")
    Integer copyTo(Integer nodeId, Integer targetNodeId, @Optional Integer insertBeforeNodeId);

    /**
     * Moves node into the new container, places it before the given anchor.
     *
     * @param nodeId             Id of the node to move.
     * @param targetNodeId       Id of the element to drop the moved node into.
     * @param insertBeforeNodeId Drop node before this one (if absent, the moved node becomes the last child of <code>targetNodeId</code>).
     * @return New id of the moved node.
     */
    @Returns("nodeId")
    Integer moveTo(Integer nodeId, Integer targetNodeId, @Optional Integer insertBeforeNodeId);

    /**
     * Undoes the last performed action.
     */
    @Experimental
    void undo();

    /**
     * Re-does the last undone action.
     */
    @Experimental
    void redo();

    /**
     * Marks last undoable state.
     */
    @Experimental
    void markUndoableState();

    /**
     * Focuses the given element.
     *
     * @param nodeId        Identifier of the node.
     * @param backendNodeId Identifier of the backend node.
     * @param objectId      JavaScript object id of the node wrapper.
     */
    @Experimental
    void focus(@Optional Integer nodeId, @Optional Integer backendNodeId,
               @Optional String objectId);

    /**
     * Sets files for the given file input element.
     *
     * @param files         Array of file paths to set.
     * @param nodeId        Identifier of the node.
     * @param backendNodeId Identifier of the backend node.
     * @param objectId      JavaScript object id of the node wrapper.
     */
    @Experimental
    void setFileInputFiles(List<String> files, @Optional Integer nodeId,
                           @Optional Integer backendNodeId, @Optional String objectId);

    /**
     * Returns boxes for the currently selected nodes.
     *
     * @param nodeId        Identifier of the node.
     * @param backendNodeId Identifier of the backend node.
     * @param objectId      JavaScript object id of the node wrapper.
     * @return Box model for the node.
     */
    @Experimental
    @Returns("model")
    BoxModel getBoxModel(@Optional Integer nodeId, @Optional Integer backendNodeId,
                         @Optional String objectId);

    /**
     * Returns node id at given location.
     *
     * @param x                         X coordinate.
     * @param y                         Y coordinate.
     * @param includeUserAgentShadowDOM False to skip to the nearest non-UA shadow root ancestor (default: false).
     * @return Id of the node at given coordinates.
     */
    @Experimental
    @Returns("nodeId")
    Integer getNodeForLocation(Integer x, Integer y, @Optional Boolean includeUserAgentShadowDOM);

    /**
     * Returns the id of the nearest ancestor that is a relayout boundary.
     *
     * @param nodeId Id of the node.
     * @return Relayout boundary node id for the given node.
     */
    @Experimental
    @Returns("nodeId")
    Integer getRelayoutBoundary(Integer nodeId);

    /**
     * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
     *
     * @param nodeId        Identifier of the node.
     * @param backendNodeId Identifier of the backend node.
     * @param objectId      JavaScript object id of the node wrapper.
     * @param depth         The maximum depth at which children should be retrieved, defaults to 1. Use -1 for the entire subtree or provide an integer larger than 0.
     * @param pierce        Whether or not iframes and shadow roots should be traversed when returning the subtree (default is false).
     * @return Node description.
     */
    @Returns("node")
    Node describeNode(@Optional Integer nodeId, @Optional Integer backendNodeId,
                      @Optional String objectId, @Experimental @Optional Integer depth,
                      @Experimental @Optional Boolean pierce);

    /**
     * Returns the root DOM node (and optionally the subtree) to the caller.
     *
     * @return Resulting node.
     */
    @Returns("root")
    Node getDocument();

    /**
     * Returns the root DOM node (and optionally the subtree) to the caller.
     *
     * @return Resulting node.
     */
    @Returns("nodes")
    List<Node> getFlattenedDocument();

    /**
     * Requests that children of the node with given id are returned to the caller in form of <tt>setChildNodes</tt> events where not only immediate children are retrieved, but all children down to the specified depth.
     *
     * @param nodeId Id of the node to get children for.
     */
    void requestChildNodes(Integer nodeId);

    /**
     * Sets attributes on element with given id. This method is useful when user edits some existing attribute value and types in several attribute name/value pairs.
     *
     * @param nodeId Id of the element to set attributes for.
     * @param text   Text with a number of attributes. Will parse this text using HTML parser.
     */
    void setAttributesAsText(Integer nodeId, String text);

    /**
     * Returns node's HTML markup.
     *
     * @return Outer HTML markup.
     */
    @Returns("outerHTML")
    String getOuterHTML();

    /**
     * Searches for a given string in the DOM tree. Use <tt>getSearchResults</tt> to access search results or <tt>cancelSearch</tt> to end this search session.
     *
     * @param query Plain text or query selector or XPath search query.
     * @return PerformSearchResult
     */
    @Experimental
    PerformSearchResult performSearch(String query);

    /**
     * Resolves the JavaScript node object for a given NodeId or BackendNodeId.
     *
     * @return JavaScript object wrapper for given node.
     */
    @Returns("object")
    RemoteObject resolveNode();

    /**
     * Creates a deep copy of the specified node and places it into the target container before the given anchor.
     *
     * @param nodeId       Id of the node to copy.
     * @param targetNodeId Id of the element to drop the copy into.
     * @return Id of the node clone.
     */
    @Experimental
    @Returns("nodeId")
    Integer copyTo(Integer nodeId, Integer targetNodeId);

    /**
     * Moves node into the new container, places it before the given anchor.
     *
     * @param nodeId       Id of the node to move.
     * @param targetNodeId Id of the element to drop the moved node into.
     * @return New id of the moved node.
     */
    @Returns("nodeId")
    Integer moveTo(Integer nodeId, Integer targetNodeId);

    /**
     * Focuses the given element.
     */
    @Experimental
    void focus();

    /**
     * Sets files for the given file input element.
     *
     * @param files Array of file paths to set.
     */
    @Experimental
    void setFileInputFiles(List<String> files);

    /**
     * Returns boxes for the currently selected nodes.
     *
     * @return Box model for the node.
     */
    @Experimental
    @Returns("model")
    BoxModel getBoxModel();

    /**
     * Returns node id at given location.
     *
     * @param x X coordinate.
     * @param y Y coordinate.
     * @return Id of the node at given coordinates.
     */
    @Experimental
    @Returns("nodeId")
    Integer getNodeForLocation(Integer x, Integer y);

    /**
     * Describes node given its id, does not require domain to be enabled. Does not start tracking any objects, can be used for automation.
     *
     * @return Node description.
     */
    @Returns("node")
    Node describeNode();
}
