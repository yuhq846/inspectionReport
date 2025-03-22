import request from '@/utils/request'

// 查询检测报告列表
export function listReport(query) {
  return request({
    url: '/system/report/list',
    method: 'get',
    params: query
  })
}

// 查询检测报告详细
export function getReport(id) {
  return request({
    url: '/system/report/' + id,
    method: 'get'
  })
}
// 查询检测报告详细
export function getReportView(query) {
  return request({
    url: '/system/report/view',
    method: 'get',
    params: query
  })
}

// 新增检测报告
export function addReport(data) {
  return request({
    url: '/system/report',
    method: 'post',
    data: data
  })
}

// 修改检测报告
export function updateReport(data) {
  return request({
    url: '/system/report',
    method: 'put',
    data: data
  })
}

// 删除检测报告
export function delReport(id) {
  return request({
    url: '/system/report/' + id,
    method: 'delete'
  })
}
