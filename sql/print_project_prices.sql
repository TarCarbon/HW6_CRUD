SELECT p.project_name AS NAME,
       SUM(w.salary * TIMESTAMPDIFF(MONTH, p.start_date, p.finish_date)) AS PRICE
FROM project_worker w
JOIN project p ON w.project_id = p.project_id
JOIN workers wr ON w.worker_id = wr.worker_id
GROUP BY p.project_id
ORDER BY PRICE DESC;
